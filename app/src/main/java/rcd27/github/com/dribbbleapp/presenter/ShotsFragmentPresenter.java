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
    View view;

    public ShotsFragmentPresenter(View view) {
        this.view = view;
    }

    @Override
    public void updateActual() {
        ApiModule
                .getDribbleApi()
                .getShots("week")
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .map(new Function<List<ShotDataTransferObject>, List<ShotVisualObject>>() {
                    @Override
                    public List<ShotVisualObject>
                    apply(@NonNull List<ShotDataTransferObject> shotDataTransferObjects)
                            throws Exception {
                        List<ShotVisualObject> result = new ArrayList<>();
                        for (ShotDataTransferObject shotDto : shotDataTransferObjects) {
                            result.add(new ShotVisualObject(shotDto.getImages().get("normal"),
                                    shotDto.getTitle(),
                                    shotDto.getDescription()));
                        }
                        return result;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(new Consumer<List<ShotVisualObject>>() {
                    @Override
                    public void accept(List<ShotVisualObject> shotVisualObjects) throws Exception {
                        view.update(shotVisualObjects);
                    }
                })
                .subscribe();
    }
}
