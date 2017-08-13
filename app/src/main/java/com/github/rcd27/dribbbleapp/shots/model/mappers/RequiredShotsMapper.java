package com.github.rcd27.dribbbleapp.shots.model.mappers;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import com.github.rcd27.dribbbleapp.shots.model.objects.ShotDataTransferObject;
import com.github.rcd27.dribbbleapp.shots.model.objects.ShotVisualObject;
import com.github.rcd27.dribbbleapp.other.Const;

/*
 * Задача этого маппера: 1) игнорировать анимированые шоты
 *                       2) цеплять картинки высокого качества, если такие имеются.
 */

public class RequiredShotsMapper
        implements Function<List<ShotDataTransferObject>, List<ShotVisualObject>> {

    @Override
    public List<ShotVisualObject> apply(@NonNull List<ShotDataTransferObject> shotDataTransferObjects) {
        List<ShotVisualObject> result = new ArrayList<>();
        for (ShotDataTransferObject shotDto : shotDataTransferObjects) {
            if (!shotDto.isAnimated) {
                if (shotDto.images.get(Const.HIDPI) != null) {
                    result.add(new ShotVisualObject(shotDto.images.get(Const.HIDPI),
                            shotDto.title,
                            shotDto.description));
                } else {
                    result.add(new ShotVisualObject(shotDto.images.get(Const.NORMAL),
                            shotDto.title,
                            shotDto.description));
                }
            }
        }
        return result;
    }
}
