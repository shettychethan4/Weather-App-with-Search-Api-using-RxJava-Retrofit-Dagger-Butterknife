package com.chethan.myapplication.di.module;

import com.chethan.myapplication.WeatherApp;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;



@Module public class AppModule {

    private WeatherApp application;

    public AppModule(WeatherApp application) {
        this.application = application;
    }

    @Provides @Singleton WeatherApp providesApplication() {
        return this.application;
    }
}
