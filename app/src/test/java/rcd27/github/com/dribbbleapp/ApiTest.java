package rcd27.github.com.dribbbleapp;


import org.junit.Test;

import java.util.List;

import rcd27.github.com.dribbbleapp.model.ApiModule;
import rcd27.github.com.dribbbleapp.model.ShotDTO;
import rx.Notification;
import rx.functions.Action1;

public class ApiTest {
    @Test
    public void mainTestCase() {
        ApiModule.getDribbleApi().getShots()
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .doOnEach(new Action1<Notification<? extends List<ShotDTO>>>() {
                    @Override
                    public void call(Notification<? extends List<ShotDTO>> notification) {
                        List<ShotDTO> requestResult = notification.getValue();
                        for (ShotDTO shotDTO : requestResult) {
                            System.out.println(shotDTO.toString());
                        }
                    }
                })
                .subscribe();
    }
}
