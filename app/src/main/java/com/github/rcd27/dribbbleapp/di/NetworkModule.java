package com.github.rcd27.dribbbleapp.di;


import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import com.github.rcd27.dribbbleapp.other.Const;
import com.github.rcd27.dribbbleapp.model.net.DribbbleApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final File cacheDir;

    public NetworkModule(File cacheDir) {
        this.cacheDir = cacheDir;
    }

    @Provides
    @Singleton
    public DribbbleApi getDribbleApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_ULR)
                .client(prepareOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(DribbbleApi.class);
    }

    private OkHttpClient prepareOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.addInterceptor(prepareLoggingInterceptor());
        httpClientBuilder.addInterceptor(prepareAuthorizationInterceptor());
        //TODO добавить оффлайн-режим, обработку ошибок с сервера.
        httpClientBuilder.cache(prepareCache());

        return httpClientBuilder.build();
    }

    private HttpLoggingInterceptor prepareLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    private Interceptor prepareAuthorizationInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header(Const.AUTHORIZATION, Const.AUTH);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    private Cache prepareCache() {
        return new Cache(cacheDir, Const.CACHE_SIZE);
    }
}
