package com.chethan.myapplication.presentation.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chethan.myapplication.R;
import com.chethan.myapplication.WeatherApp;
import com.chethan.myapplication.di.component.MainComponent;
import com.chethan.myapplication.presentation.view.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;



public abstract class BaseActivity extends AppCompatActivity {

    protected final static int SETTINGS_ACTION = 99;

    @BindView(R.id.toolbar) Toolbar toolbar;

    private WeatherApp getWeatherApp() {
        return (WeatherApp) getApplication();
    }

    protected MainComponent getMainComponent() {
        return getWeatherApp().getMainComponent();
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }



    @Override public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, SETTINGS_ACTION);
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract int getLayoutId();
}
