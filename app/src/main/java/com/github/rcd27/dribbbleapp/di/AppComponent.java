package com.github.rcd27.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Component;
import com.github.rcd27.dribbbleapp.shots.data.ShotsModelImpl;
import com.github.rcd27.dribbbleapp.utils.NetworkModule;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsFragmentPresenter;
import com.github.rcd27.dribbbleapp.shots.view.adapters.ShotsFragmentListAdapter;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class,
        ShotsModule.class})

public interface AppComponent {

    void inject(ShotsFragmentPresenter shotsFragmentPresenter);

    void inject(ShotsModelImpl model);

    void inject(ShotsFragmentListAdapter shotsFragmentListAdapter);

}
