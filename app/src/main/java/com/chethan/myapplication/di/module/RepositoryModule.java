package com.chethan.myapplication.di.module;

import com.chethan.myapplication.data.remote.repository.WeatherRepository;
import com.chethan.myapplication.data.remote.repository.WeatherRepositoryImpl;
import com.chethan.myapplication.data.remote.service.WeatherService;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;



@Module public class RepositoryModule {

    @Provides @Singleton
    WeatherRepository providesWeatherRepository(WeatherService service) {
        return new WeatherRepositoryImpl(service);
    }
}
