package rcd27.github.com.dribbbleapp;


import android.app.Application;
import android.util.Log;

import rcd27.github.com.dribbbleapp.di.ApiModule;
import rcd27.github.com.dribbbleapp.di.AppComponent;
import rcd27.github.com.dribbbleapp.di.DaggerAppComponent;

/* 1. Игнорировать анимированные картинки √
 * 2. Использовать картинки высокого качества, если такие поддерживаются √
 * 3. Внедрить оффлайн кэширование: при перезапуске приложения всё должно цепляться с диска
 * 4. Каждый шот дожен иметь название и описание. Описание не больше двух строк. √
 * 5. Размер шота не больше половины экрана √
 * 6. Внедрить "Pull down to refresh". √
 */

public class DribbbleApplication extends Application {

    private static DribbbleApplication instance;

    public static DribbbleApplication getInstance() {
        return instance;
    }

    //TODO сделать сабкомпонент для ShotSwipeRefreshFragment
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule(getCacheDir()))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
