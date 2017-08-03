package rcd27.github.com.dribbbleapp;


import org.junit.Test;

import java.util.List;

import rcd27.github.com.dribbbleapp.model.ApiModule;
import rcd27.github.com.dribbbleapp.model.ShotDataTransferObject;
import rx.Notification;
import rx.functions.Action1;

public class ApiTest {
    @Test
    public void mainTestCase() {
        ApiModule.getDribbleApi().getShots("week")
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .doOnEach(new Action1<Notification<? extends List<ShotDataTransferObject>>>() {
                    @Override
                    public void call(Notification<? extends List<ShotDataTransferObject>> notification) {
                        List<ShotDataTransferObject> requestResult = notification.getValue();
                        boolean hasAnimatedShots = false;
                        for (ShotDataTransferObject shotDataTransferObject : requestResult) {
                            if (shotDataTransferObject.isAnimated()) {
                                hasAnimatedShots = true;
                            }
                        }
                        System.out.println("Has animated shots: " + hasAnimatedShots);
                        System.out.println("Shots quantity: " + requestResult.size());
                    }
                })
                .subscribe();
    }
}
