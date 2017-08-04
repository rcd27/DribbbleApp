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
    private final static String TAG = "eventLog";

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: application created.");
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule(getCacheDir()))
                .build();
    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "onTerminate: application terminated");
        super.onTerminate();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
