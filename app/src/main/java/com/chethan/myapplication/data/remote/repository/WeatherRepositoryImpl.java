package com.chethan.myapplication.data.remote.repository;

import com.chethan.myapplication.data.remote.service.WeatherService;
import com.chethan.myapplication.domain.models.forecastmodel.ForecastData;
import com.chethan.myapplication.domain.models.weathermodel.WeatherData;

import io.reactivex.Observable;



public class WeatherRepositoryImpl implements WeatherRepository {

    private final WeatherService weatherService;

    public WeatherRepositoryImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override public Observable<WeatherData> getWeatherByCity(String cityName, String units) {
        return weatherService.getWeatherByCity(cityName, units);
    }

    @Override public Observable<ForecastData> getForecastByCity(String cityName, String units) {
        return weatherService.getForecastByCity(cityName, units);
    }

    @Override
    public Observable<WeatherData> getWeatherByLocation(String lat, String lon, String units) {
        return weatherService.getWeatherByLocation(lat, lon, units);
    }

    @Override
    public Observable<ForecastData> getForecastByLocation(String lat, String lon, String units) {
        return weatherService.getForecastByLocation(lat, lon, units);
    }
}
