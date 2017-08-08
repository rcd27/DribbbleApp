package com.github.rcd27.dribbbleapp.presenter;


import javax.inject.Inject;

import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.model.mappers.RequiredShotsMapper;
import com.github.rcd27.dribbbleapp.model.Model;
import com.github.rcd27.dribbbleapp.view.View;

public class ShotsFragmentPresenter implements Presenter {

    private final View view;

    @Inject
    public Model model;

    // Для переключения страниц. Можно использовать Link Header.
    // см.: http://developer.dribbble.com/v1/#pagination
    private int pageNumber = 1;

    public ShotsFragmentPresenter(View view) {
        this.view = view;
        DribbbleApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void updateActual() {
        model.getShots(pageNumber, 50)
                .map(new RequiredShotsMapper())
                .doOnSuccess(view::update)
                .subscribe();
        pageNumber++;
    }


}
