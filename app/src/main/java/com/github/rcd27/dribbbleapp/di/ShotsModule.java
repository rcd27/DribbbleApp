package com.github.rcd27.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.github.rcd27.dribbbleapp.shots.model.ShotsModel;
import com.github.rcd27.dribbbleapp.shots.model.ShotsModelImpl;

@Module
public class ShotsModule {

    @Provides
    @Singleton
    ShotsModel provideModel() {
        return new ShotsModelImpl();
    }
}
