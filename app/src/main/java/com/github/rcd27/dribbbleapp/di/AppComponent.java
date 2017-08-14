package com.github.rcd27.dribbbleapp.di;

import com.github.rcd27.dribbbleapp.utils.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})

public interface AppComponent {

    ShotsComponent plus(ShotsModule shotsModule);

}
