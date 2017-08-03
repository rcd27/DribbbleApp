package rcd27.github.com.dribbbleapp.model;


import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitRequest {
    @GET("/v1/shots")
    Single<List<ShotDataTransferObject>> getShots(@Query("timeframe") String listCommand);
}
