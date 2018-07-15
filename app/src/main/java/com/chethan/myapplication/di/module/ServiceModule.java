package com.chethan.myapplication.di.module;

import com.chethan.myapplication.data.remote.service.WeatherService;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;




@Module public class ServiceModule {

    @Provides @Singleton
    WeatherService providesWeatherServices(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }
}
