package com.chethan.myapplication.domain.models;

import com.chethan.myapplication.domain.models.forecastmodel.ForecastData;
import com.chethan.myapplication.domain.models.weathermodel.WeatherData;




public class CombinedData {

    private WeatherData mWeatherData;
    private ForecastData mForecastData;

    public CombinedData(WeatherData weatherData, ForecastData forecastData) {
        mWeatherData = weatherData;
        mForecastData = forecastData;
    }

    public ForecastData getForecastData() {
        return mForecastData;
    }

    public WeatherData getWeatherData() {
        return mWeatherData;
    }
}
