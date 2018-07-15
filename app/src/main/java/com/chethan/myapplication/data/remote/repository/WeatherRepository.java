package com.chethan.myapplication.data.remote.repository;

import com.chethan.myapplication.domain.models.forecastmodel.ForecastData;
import com.chethan.myapplication.domain.models.weathermodel.WeatherData;

import io.reactivex.Observable;



public interface WeatherRepository {

    Observable<WeatherData> getWeatherByCity(String cityName, String units);

    Observable<ForecastData> getForecastByCity(String cityName, String units);

    Observable<WeatherData> getWeatherByLocation(String lat, String lon, String units);

    Observable<ForecastData> getForecastByLocation(String lat, String lon, String units);
}
