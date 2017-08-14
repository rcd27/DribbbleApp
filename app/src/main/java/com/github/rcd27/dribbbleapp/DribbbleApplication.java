package com.github.rcd27.dribbbleapp;


import android.app.Application;

import com.github.rcd27.dribbbleapp.di.AppComponent;
import com.github.rcd27.dribbbleapp.di.ApplicationModule;
import com.github.rcd27.dribbbleapp.di.DaggerAppComponent;
import com.github.rcd27.dribbbleapp.utils.NetworkModule;

public class DribbbleApplication extends Application {

    private static DribbbleApplication instance;

    public static DribbbleApplication getInstance() {
        return instance;
    }

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
    }

    protected void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(
                        "https://api.dribbble.com/v1/",
                        "Bearer d40d9ad2e7a946e27e922ac609b84ff86a91223585208473a821aa394c602003"))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
