package com.github.rcd27.dribbbleapp;


import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsFragmentPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

public class ShotsPresenterTest {

    @Mock
    private ShotsContract.View shotsView;

    private ShotsFragmentPresenter shotsFragmentPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        shotsFragmentPresenter = new ShotsFragmentPresenter(shotsView);
    }

    @Test
    public void updateActualTest() {
        shotsFragmentPresenter.updateActual();

        verify(shotsView).update(new ArrayList<>());

    }
}
