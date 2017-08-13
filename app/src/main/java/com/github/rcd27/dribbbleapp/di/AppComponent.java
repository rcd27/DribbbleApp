package com.github.rcd27.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Component;
import com.github.rcd27.dribbbleapp.shots.model.ShotsModelImpl;
import com.github.rcd27.dribbbleapp.utils.NetworkModule;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsFragmentShotsPresenter;
import com.github.rcd27.dribbbleapp.shots.view.adapters.ShotsFragmentListAdapter;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class,
        ShotsModule.class,
        ModelModule.class})

public interface AppComponent {

    void inject(ShotsFragmentShotsPresenter shotsFragmentPresenter);

    void inject(ShotsModelImpl model);

    void inject(ShotsFragmentListAdapter shotsFragmentListAdapter);

}
