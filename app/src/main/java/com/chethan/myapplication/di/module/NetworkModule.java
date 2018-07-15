package com.chethan.myapplication.di.module;

import com.chethan.myapplication.WeatherApp;
import com.chethan.myapplication.domain.schedulers.SchedulerProvider;
import com.chethan.myapplication.utils.Constants;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;




@Module public class NetworkModule {

    @Provides @Singleton Cache provideOkHttpCache(WeatherApp application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client =
            new OkHttpClient.Builder().addInterceptor(interceptor).cache(cache);
        return client.build();
    }

    @Provides @Singleton Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    }

    @Provides @Singleton
    SchedulerProvider schedulerProvider() {
        return new SchedulerProvider();
    }
}
