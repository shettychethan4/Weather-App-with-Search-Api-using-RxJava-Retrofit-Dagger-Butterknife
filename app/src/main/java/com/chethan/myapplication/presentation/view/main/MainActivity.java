package com.chethan.myapplication.presentation.view.main;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.chethan.myapplication.R;
import com.chethan.myapplication.data.remote.repository.WeatherRepository;
import com.chethan.myapplication.domain.models.CombinedData;
import com.chethan.myapplication.domain.schedulers.SchedulerProvider;
import com.chethan.myapplication.presentation.adapters.ViewPagerAdapter;
import com.chethan.myapplication.presentation.common.BaseActivity;
import com.chethan.myapplication.presentation.view.settings.SettingsFragment;
import com.chethan.myapplication.utils.PrefsManager;
import com.chethan.myapplication.utils.location.LocationProvider;
import com.google.android.gms.maps.model.LatLng;
import butterknife.BindView;
import javax.inject.Inject;



public class MainActivity extends BaseActivity
    implements LocationProvider.CustomLocationListener, MainContract.View,Search {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    @Inject
    SchedulerProvider schedulerProvider;
    @Inject
    WeatherRepository weatherRepository;

    @BindView(R.id.viewpager) ViewPager mViewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private ViewPagerAdapter mAdapter;
    private PrefsManager prefs;
    private LocationProvider mLocationProvider;
    private Location mLocation;
    private String mTempUnit = "";
    private MainContract.Presenter mainPresenter;




    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       getMainComponent().inject(this);

        initUi();

        initPresenter();

        initPrefs();

    }



    @Override protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initUi() {
        tabLayout.setupWithViewPager(mViewPager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setVisibility(View.INVISIBLE);
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this, weatherRepository, schedulerProvider);
    }

    private void initPrefs() {
        prefs = PrefsManager.from(this);
        mTempUnit = prefs.getTempUnit();
        getLocationPrefs();
    }

    private void getLocationPrefs() {
        if (prefs.getLocationToggle()) {
            if (mLocationProvider == null) {
                mLocationProvider = new LocationProvider(this, this);
            }
        } else {
            getCombinedDataByCity();
        }
    }

    @Override public void onLocationFetched(Location location) {
        mLocation = new Location(location);
        getCombinedDataByLocation();
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Log.i(TAG, "User agreed to make required location settings changes.");
                    mLocationProvider.startLocationUpdates();
                    break;
                case Activity.RESULT_CANCELED:
                    Log.i(TAG, "User chose not to make required location settings changes.");
                    getCombinedDataByCity();
                    prefs.setLocationToggle(false);
                    break;
            }
        } else if (requestCode == SETTINGS_ACTION) {
            if (resultCode == SettingsFragment.PREFS_UPDATED) {
                Log.i(TAG, "User made changes to preferences");
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void getCombinedDataByCity() {
        if (prefs != null) {
            showProgressBar();
            String selectedCity = prefs.getSelectedCity();
            mainPresenter.getDataByCity(selectedCity, mTempUnit);
        }
    }

    @Override public void onDataByCityRetrieved(CombinedData data) {
        updateFragments(data);
        mViewPager.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    @Override public void onDataByCityFailed() {
        hideProgressBar();
    }

    private void getCombinedDataByLocation() {
        showProgressBar();
        mainPresenter.getDataByLocation(String.valueOf(mLocation.getLatitude()),
            String.valueOf(mLocation.getLongitude()), mTempUnit);
    }

    @Override public void onDataByLocationRetrieved(CombinedData data) {
        updateFragments(data);
        mViewPager.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    @Override public void onDataByLocationFailed() {
        hideProgressBar();
    }

    private void updateFragments(CombinedData combinedData) {
        ((WeatherFragment) mAdapter.getRegisteredFragment(0)).updateData(
            combinedData.getWeatherData());
        ((ForecastFragment) mAdapter.getRegisteredFragment(1)).updateData(
            combinedData.getForecastData());
    }

    @Override protected void onResume() {
        super.onResume();
        if (mLocationProvider != null) {
            mLocationProvider.connect();
        }
    }

    @Override protected void onStop() {
        super.onStop();
        if (mLocationProvider != null) {
            mLocationProvider.disconnect();
        }
    }

    @Override protected void onPause() {
        super.onPause();
        if (mLocationProvider != null) {
            if (mLocationProvider.isConnected()) {
                mLocationProvider.stopLocationUpdates();
            }
        }
    }

    @Override protected void onDestroy() {
        mainPresenter.unsubscribe();
        super.onDestroy();
    }

    private void showProgressBar() {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void get(LatLng latLng) {
        if(latLng != null){
            showProgressBar();
            mainPresenter.getDataByLocation(String.valueOf(latLng.latitude),
                    String.valueOf(latLng.longitude), mTempUnit);
        }
    }
}