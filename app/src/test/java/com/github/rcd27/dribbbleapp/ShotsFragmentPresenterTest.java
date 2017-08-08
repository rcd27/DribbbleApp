package com.github.rcd27.dribbbleapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import com.github.rcd27.dribbbleapp.presenter.ShotsFragmentPresenter;
import com.github.rcd27.dribbbleapp.view.View;

@RunWith(MockitoJUnitRunner.class)
public class ShotsFragmentPresenterTest {

    @Mock
    View view;

    ShotsFragmentPresenter shotsFragmentPresenter;

    @Before
    public void setUp() {
        shotsFragmentPresenter = new ShotsFragmentPresenter(view);

        RxJavaPlugins.setIoSchedulerHandler(
                sheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                sheduler -> Schedulers.trampoline()
        );
    }

    @After
    public void finish() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }

    @Test
    public void updateActual_showSuccess() {
        shotsFragmentPresenter.updateActual();
        Mockito.verify(view).update(new ArrayList<>());
    }

}
