package com.chethan.myapplication.presentation.view.main;


import com.chethan.myapplication.domain.models.CombinedData;

public interface MainContract {

    interface View {

        void onDataByCityRetrieved(CombinedData data);

        void onDataByCityFailed();

        void onDataByLocationRetrieved(CombinedData data);

        void onDataByLocationFailed();
    }

    interface Presenter {

        void getDataByCity(String cityName, String unit);

        void getDataByLocation(String lat, String lon, String unit);

        void unsubscribe();
    }
}
