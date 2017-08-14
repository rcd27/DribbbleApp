package com.github.rcd27.dribbbleapp;


import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import dagger.Module;

import static org.mockito.Mockito.verify;

public class ShotsPresenterTest {

    @Mock
    private ShotsContract.View shotsView;

    private ShotsPresenter shotsPresenter;

    @Mock
    private ShotsContract.Interactor interactor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

//        shotsPresenter = new ShotsPresenter(interactor, shotsView);
    }

    @Test
    public void updateActualTest() {
//        shotsPresenter.updateActual();

        verify(shotsView).update(new ArrayList<>());

    }
}
