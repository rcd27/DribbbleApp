package rcd27.github.com.dribbbleapp.presenter;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import rcd27.github.com.dribbbleapp.model.ApiModule;
import rcd27.github.com.dribbbleapp.model.ShotDataTransferObject;
import rcd27.github.com.dribbbleapp.model.ShotVisualObject;
import rcd27.github.com.dribbbleapp.view.View;

public class ShotsFragmentPresenter implements Presenter {
    private View view;

    public ShotsFragmentPresenter(View view) {
        this.view = view;
    }

    @Override
    public void updateActual() {
        ApiModule
                .getDribbleApi()
                .getShots("week")
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
                if (!shotDto.isAnimated()) {
                    //TODO напсать тест "Что, если нет "hidpi".
                    try {
                        result.add(new ShotVisualObject(shotDto.getImages().get("hidpi"),
                                shotDto.getTitle(),
                                shotDto.getDescription()));
                    } catch (Exception e) {
                        result.add(new ShotVisualObject(shotDto.getImages().get("normal"),
                                shotDto.getTitle(),
                                shotDto.getDescription()));
                    }
                }
            }
            return result;
        }
    }
}
