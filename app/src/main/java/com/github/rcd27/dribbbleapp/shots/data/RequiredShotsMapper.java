package com.github.rcd27.dribbbleapp.shots.data;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/*
 * Задача этого маппера: 1) игнорировать анимированые шоты
 *                       2) цеплять картинки высокого качества, если такие имеются.
 */

public class RequiredShotsMapper
        implements Function<List<ShotDataTransferObject>, List<ShotVisualObject>> {

    private static final String HIDPI = "hidpi";
    private static final String NORMAL = "normal";

    @Override
    public List<ShotVisualObject> apply(@NonNull List<ShotDataTransferObject> shotDataTransferObjects) {
        List<ShotVisualObject> result = new ArrayList<>();
        for (ShotDataTransferObject shotDto : shotDataTransferObjects) {
            if (!shotDto.isAnimated) {
                if (shotDto.images.get(HIDPI) != null) {
                    result.add(new ShotVisualObject(shotDto.images.get(HIDPI),
                            shotDto.title,
                            shotDto.description));
                } else {
                    result.add(new ShotVisualObject(shotDto.images.get(NORMAL),
                            shotDto.title,
                            shotDto.description));
                }
            }
        }
        return result;
    }
}
