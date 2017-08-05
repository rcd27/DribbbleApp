package rcd27.github.com.dribbbleapp.di;


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
import rcd27.github.com.dribbbleapp.model.DribbbleApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTH = "Bearer d40d9ad2e7a946e27e922ac609b84ff86a91223585208473a821aa394c602003";
    private static final String BASE_ULR = "https://api.dribbble.com/v1/";
    private static final long CACHE_SIZE = 10 * 1024 * 1024;

    private final File cacheDir;

    public NetworkModule(File cacheDir) {
        this.cacheDir = cacheDir;
    }

    //TODO разбить по маленьким отдельным методам для большей читаемости.
    @Provides
    @Singleton
    public DribbbleApi getDribbleApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ULR)
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
                        .header(AUTHORIZATION, AUTH);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    private Cache prepareCache() {
        return new Cache(cacheDir, CACHE_SIZE);
    }
}
