package rcd27.github.com.dribbbleapp.presenter;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import rcd27.github.com.dribbbleapp.DribbbleApplication;
import rcd27.github.com.dribbbleapp.model.DribbbleApi;
import rcd27.github.com.dribbbleapp.model.ShotDataTransferObject;
import rcd27.github.com.dribbbleapp.model.ShotVisualObject;
import rcd27.github.com.dribbbleapp.view.View;

public class ShotsFragmentPresenter implements Presenter {
    private static final String HIDPI = "hidpi";
    private static final String NORMAL = "normal";

    private final View view;

    @Inject
    public DribbbleApi dribbbleApi;

    //TODO пробросить через даггер
    public ShotsFragmentPresenter(View view) {
        this.view = view;
        DribbbleApplication.getAppComponent().inject(this);
    }

    @Override
    public void updateActual() {
        dribbbleApi
                .getShots("ever")
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .map(new CustomMapper())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(new Consumer<List<ShotVisualObject>>() {
                    @Override
                    public void accept(List<ShotVisualObject> shotVisualObjects) throws Exception {
                        view.update(shotVisualObjects);
                    }
                })
                .subscribe();
    }

    private class CustomMapper
            implements Function<List<ShotDataTransferObject>, List<ShotVisualObject>> {

        @Override
        public List<ShotVisualObject> apply(@NonNull List<ShotDataTransferObject>
                                                    shotDataTransferObjects) throws Exception {
            List<ShotVisualObject> result = new ArrayList<>();
            for (ShotDataTransferObject shotDto : shotDataTransferObjects) {
                //TODO написать тесты
                if (!shotDto.isAnimated()) {
                    if (shotDto.getImages().get(HIDPI) != null) {
                        result.add(new ShotVisualObject(shotDto.getImages().get(HIDPI),
                                shotDto.getTitle(),
                                shotDto.getDescription()));
                    } else {
                        result.add(new ShotVisualObject(shotDto.getImages().get(NORMAL),
                                shotDto.getTitle(),
                                shotDto.getDescription()));
                    }
                }
            }
            return result;
        }
    }
}
