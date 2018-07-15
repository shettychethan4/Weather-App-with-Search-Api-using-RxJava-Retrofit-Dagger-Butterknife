package com.chethan.myapplication;

import android.app.Application;
import android.content.Context;

import com.chethan.myapplication.di.component.DaggerMainComponent;
import com.chethan.myapplication.di.component.MainComponent;
import com.chethan.myapplication.di.module.AppModule;


public class WeatherApp extends Application {

    private static Context mContext;
    private MainComponent mainComponent;

    @Override public void onCreate() {
        super.onCreate();

        initDagger();
        mContext = this;
        // LeakCanary.install(this);
    }

    private void initDagger() {

        mainComponent = DaggerMainComponent.builder().appModule(new AppModule(this)).build();
    }

    public MainComponent getMainComponent(){
        return mainComponent;
    }

    //http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context/4391811#4391811
    public static Context getContext() {
        return mContext;
    }
}
