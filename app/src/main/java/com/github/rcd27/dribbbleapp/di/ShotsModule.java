package com.github.rcd27.dribbbleapp.di;

import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.shots.data.RequiredShotsMapper;
import com.github.rcd27.dribbbleapp.shots.data.ShotsInteractor;
import com.github.rcd27.dribbbleapp.shots.data.ShotsModelImpl;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsPresenter;
import com.github.rcd27.dribbbleapp.utils.ConnectivityUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class ShotsModule {

    private final ShotsContract.View view;

    public ShotsModule(ShotsContract.View view) {
        this.view = view;
    }

    @Provides
    ShotsContract.Presenter provideShotsPresenter(ShotsContract.Interactor shotsInteractor) {
        return new ShotsPresenter(shotsInteractor, view);
    }

    //TODO спрятать маппер в слой Model.
    @Provides
    ShotsContract.Interactor provideShotsInteractor(ShotsContract.Model model,
                                                    ConnectivityUtils connectivityUtils,
                                                    RequiredShotsMapper requiredShotsMapper) {
        return new ShotsInteractor(model, connectivityUtils, requiredShotsMapper);
    }

    @Provides
    ShotsContract.Model provideModel() {
        return new ShotsModelImpl();
    }

    @Provides
    RequiredShotsMapper provideMapper() {
        return new RequiredShotsMapper();
    }
}
