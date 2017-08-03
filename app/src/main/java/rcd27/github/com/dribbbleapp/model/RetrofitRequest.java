package rcd27.github.com.dribbbleapp.model;


import java.util.List;

import retrofit2.http.GET;
import rx.Single;

public interface RetrofitRequest {
    @GET("/v1/shots")
    Single<List<ShotDTO>> getShots();
}
