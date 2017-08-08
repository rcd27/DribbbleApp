package com.github.rcd27.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.github.rcd27.dribbbleapp.model.Model;
import com.github.rcd27.dribbbleapp.model.ModelImpl;

@Module
public class ShotsFragmentPresenterModule {

    @Provides
    @Singleton
    Model provideModel() {
        return new ModelImpl();
    }
}
