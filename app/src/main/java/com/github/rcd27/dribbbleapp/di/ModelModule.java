package com.github.rcd27.dribbbleapp.di;

import com.github.rcd27.dribbbleapp.other.Const;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ModelModule {

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideUIScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideIOScheduler() {
        return Schedulers.io();
    }

}
