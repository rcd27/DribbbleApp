package com.github.rcd27.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Component;
import com.github.rcd27.dribbbleapp.model.ModelImpl;
import com.github.rcd27.dribbbleapp.model.net.NetworkModule;
import com.github.rcd27.dribbbleapp.presenter.ShotsFragmentPresenter;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class,
        ShotsFragmentPresenterModule.class,
        ModelModule.class})

public interface AppComponent {

    void inject(ShotsFragmentPresenter shotsFragmentPresenter);

    void inject(ModelImpl model);

}
