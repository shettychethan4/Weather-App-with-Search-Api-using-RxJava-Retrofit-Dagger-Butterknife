package com.chethan.myapplication.presentation.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.chethan.myapplication.R;
import com.chethan.myapplication.domain.models.weathermodel.WeatherData;
import com.chethan.myapplication.presentation.adapters.WeatherAdapter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;



public class WeatherFragment extends Fragment implements PlaceSelectionListener {



    private WeatherAdapter mWeatherAdapter;
    private Search search ;
    private LatLng latlong;
    private PlaceAutocompleteFragment autocompleteFragment;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherAdapter = new WeatherAdapter(getActivity());
    }




    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

         autocompleteFragment  = (PlaceAutocompleteFragment)getActivity()
                .getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);

        return view;
    }


    @Override public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mWeatherAdapter);
    }

    public void updateData(WeatherData weatherData) {
        mWeatherAdapter.setWeatherData(weatherData);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            search = (Search) context;
        }catch (Exception ex){}
    }


    @Override
    public void onPlaceSelected(Place place) {
        latlong =  place.getLatLng();
        search.get(latlong);
    }

    @Override
    public void onError(Status status) {
        Toast.makeText(getActivity(),getActivity().getText(R.string.network_error),Toast.LENGTH_SHORT).show();
    }
}
