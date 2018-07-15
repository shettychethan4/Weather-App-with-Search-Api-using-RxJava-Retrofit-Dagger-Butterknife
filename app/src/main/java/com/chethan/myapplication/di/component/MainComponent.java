package com.chethan.myapplication.di.component;

import com.chethan.myapplication.di.module.AppModule;
import com.chethan.myapplication.di.module.NetworkModule;
import com.chethan.myapplication.di.module.RepositoryModule;
import com.chethan.myapplication.di.module.ServiceModule;
import com.chethan.myapplication.presentation.view.main.MainActivity;
import com.chethan.myapplication.presentation.view.settings.SettingsActivity;

import dagger.Component;
import javax.inject.Singleton;


@Singleton @Component(modules = {
    AppModule.class, NetworkModule.class, ServiceModule.class, RepositoryModule.class
}) public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(SettingsActivity settingsActivity);
}
