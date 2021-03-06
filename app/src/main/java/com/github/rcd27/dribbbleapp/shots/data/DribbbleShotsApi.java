package com.github.rcd27.dribbbleapp.shots.data;


import java.util.List;

import io.reactivex.Single;
import com.github.rcd27.dribbbleapp.shots.data.ShotDataTransferObject;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DribbbleShotsApi {

    @GET("shots")
    Single<List<ShotDataTransferObject>> getShots(@Query("page") int pageNumber,
                                                  @Query("per_page") int shotsPerPage);

}
