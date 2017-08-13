package com.github.rcd27.dribbbleapp;


import android.app.Application;

import com.github.rcd27.dribbbleapp.di.AppComponent;
import com.github.rcd27.dribbbleapp.di.ApplicationModule;
import com.github.rcd27.dribbbleapp.di.DaggerAppComponent;
import com.github.rcd27.dribbbleapp.utils.NetworkModule;

/**
 * 1. Игнорировать анимированные картинки √
 * 2. Использовать картинки высокого качества, если такие поддерживаются √
 * 3. Внедрить оффлайн кэширование: при перезапуске приложения всё должно цепляться с диска √
 * 4. Каждый шот дожен иметь название и описание. Описание не больше двух строк. √
 * 5. Размер шота не больше половины экрана √
 * 6. Внедрить "Pull down to refresh". √
 * 7. Код должен быть покрыт тестами. X
 */

/** TODO:
 * 1.Перейти от нечитаемой структуры 'package by layer' к 'package by feature'. .shots .shotdetail и т.д.
 */

public class DribbbleApplication extends Application {

    private static DribbbleApplication instance;

    public static DribbbleApplication getInstance() {
        return instance;
    }

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
    }

    protected void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(
                        "https://api.dribbble.com/v1/",
                        "Bearer d40d9ad2e7a946e27e922ac609b84ff86a91223585208473a821aa394c602003"))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
