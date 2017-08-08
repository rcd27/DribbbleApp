package com.github.rcd27.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Component;
import com.github.rcd27.dribbbleapp.model.ModelImpl;
import com.github.rcd27.dribbbleapp.presenter.ShotsFragmentPresenter;

//TODO FIXME: реализовать через сабкомпонент.
@Singleton
@Component(modules = {NetworkModule.class,
        ShotsFragmentPresenterModule.class,
        ModelModule.class})
public interface AppComponent {

    void inject(ShotsFragmentPresenter shotsFragmentPresenter);

    void inject(ModelImpl model);
}
