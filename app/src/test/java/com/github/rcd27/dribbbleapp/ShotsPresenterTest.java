package com.github.rcd27.dribbbleapp;


import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.shots.data.ShotVisualObject;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ShotsPresenterTest {

    @Mock
    private ShotsContract.View shotsView;

    private ShotsContract.Presenter shotsPresenter;

    @Mock
    private ShotsContract.Interactor interactor;

    @Before
    public void setUp() {
        //TODO FIXME: после поднятия версии Mockito перестал работать.
        MockitoAnnotations.initMocks(this);

        shotsPresenter = new ShotsPresenter(interactor, shotsView);
    }

    @Test
    public void updateTestWithFakeShots() {
        //given
        List<ShotVisualObject> testShots = getTestShots();
        given(interactor.getFithtyShotsForOnePage()).willReturn(Single.just(testShots));

        //when
        shotsPresenter.updateActual();

//        then
        verify(shotsView).update(testShots);
    }

    @Test
    public void outOfInternetConnectionTest() {
        shotsPresenter.checkIfOnlineAndUpdateActual();
        verify(shotsView).showError(ShotsPresenter.NO_INTERNET_CONNECTION);
    }

    private List<ShotVisualObject> getTestShots() {
        return new ArrayList<ShotVisualObject>() {{
            add(new ShotVisualObject("mock.image.url1", "Mock Image#1", "How do you think what's this?"));
            add(new ShotVisualObject("mock.image.url2", "Mock Image#2", "What about thinkin'?"));
            add(new ShotVisualObject("mock.image.url3", "Mock Image#3", "U still don't know?"));

        }};
    }
}
