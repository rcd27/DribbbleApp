package com.github.rcd27.dribbbleapp.presenter;


import javax.inject.Inject;

import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.model.mappers.RequiredShotsMapper;
import com.github.rcd27.dribbbleapp.model.Model;
import com.github.rcd27.dribbbleapp.view.View;
import com.github.rcd27.dribbbleapp.view.fragments.ShotsFragmentView;

public class ShotsFragmentPresenter implements Presenter {

    @Inject
    public Model model;

    private  ShotsFragmentView view;

    @Inject
    public RequiredShotsMapper requiredShotsMapper;

    // Для переключения страниц. Можно использовать Link Header.
    // см.: http://developer.dribbble.com/v1/#pagination
    private int pageNumber = 1;

    @Inject
    public ShotsFragmentPresenter() {
    }

    public ShotsFragmentPresenter(ShotsFragmentView view) {
        this.view = view;
        DribbbleApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void updateActual() {
        model.getShots(pageNumber, 50)
                .map(requiredShotsMapper)
                .doOnSuccess(view::update)
                .subscribe();
        pageNumber++;
    }
}
