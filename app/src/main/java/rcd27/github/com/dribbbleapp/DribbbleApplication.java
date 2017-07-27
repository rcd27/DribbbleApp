package rcd27.github.com.dribbbleapp;


import android.app.Application;
import android.util.Log;

/* 1. Игнорировать анимированные картинки
 * 2. Использовать картинки высокого качества, если такие поддерживаются
 * 3. Внедрить оффлайн кэширование: при перезапуске приложения всё должно цепляться с диска
 * 4. Каждый шот дожен иметь название и описание. Описание не больше двух строк.
 * 5. Размер шота не больше половины экрана
 * 6. Внедрить "Pull down to refresh".
 */

public class DribbbleApplication extends Application {
    private final static String TAG = "eventLog";

    @Override
    public void onCreate() {
//        TODO: СОЗДАТЬ МОДЕЛЬ, ЗАРЕГИСТРИРОВАТЬ ПРИЛОЖЕНИЕ И СДЕЛАТЬ ТЕСТОВЫЙ ПРОГОН.
        Log.d(TAG, "onCreate: application created.");
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "onTerminate: application terminated");
        super.onTerminate();
    }
}
