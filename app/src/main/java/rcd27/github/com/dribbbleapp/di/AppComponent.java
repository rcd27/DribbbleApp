package rcd27.github.com.dribbbleapp.di;

import javax.inject.Singleton;

import dagger.Component;
import rcd27.github.com.dribbbleapp.presenter.ShotsFragmentPresenter;

//TODO FIXME: реализовать через сабкомпонент.
@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(ShotsFragmentPresenter shotsFragmentPresenter);

}
