package com.github.rcd27.dribbbleapp.di;

import com.github.rcd27.dribbbleapp.shots.data.ShotsModelImpl;
import com.github.rcd27.dribbbleapp.shots.view.ShotsListAdapter;
import com.github.rcd27.dribbbleapp.utils.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class})

public interface AppComponent {

    ShotsComponent plus(ShotsModule shotsModule);

    //TODO убрать. Отныне инжектимся только в фрагменты.
    @Deprecated
    void inject(ShotsModelImpl model);

    @Deprecated
    void inject(ShotsListAdapter shotsListAdapter);

}
