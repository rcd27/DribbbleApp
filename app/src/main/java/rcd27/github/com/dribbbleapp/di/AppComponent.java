package rcd27.github.com.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Component;
import rcd27.github.com.dribbbleapp.presenter.ShotsFragmentPresenter;

@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent {

    void inject(ShotsFragmentPresenter shotsFragmentPresenter);

}
