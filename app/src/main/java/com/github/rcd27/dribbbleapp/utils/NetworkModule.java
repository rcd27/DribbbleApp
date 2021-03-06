package com.github.rcd27.dribbbleapp.utils;


import android.content.Context;

import com.github.rcd27.dribbbleapp.shots.data.DribbbleShotsApi;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String CACHE_PATH = "httpCache";
    private static final int CONNECT_TIMEOUT = 10;
    private static final String AUTHORIZATION = "Authorization";

    private final String dribbbleBaseUrl;
    private final String dribbbleAuthKey;

    public NetworkModule(String dribbbleBaseUrl, String dribbbleAuthKey) {
        this.dribbbleBaseUrl = dribbbleBaseUrl;
        this.dribbbleAuthKey = dribbbleAuthKey;
    }

    @Provides
    @Singleton
    public ConnectivityUtils provideConnectivityUtils(Context context) {
        return new ConnectivityUtils(context);
    }

    @Provides
    @Singleton
    public DribbbleShotsApi provideApiInterface(Context context) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(provideAuthInterceptor())
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(getCache(context))
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(dribbbleBaseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build()
                .create(DribbbleShotsApi.class);
    }

    private Interceptor provideAuthInterceptor() {
        return chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header(AUTHORIZATION, dribbbleAuthKey);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private Interceptor provideOfflineCacheInterceptor() {
        return chain -> {
            Request request = chain.request();

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build();

            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();

            return chain.proceed(request);
        };
    }

    private Interceptor provideCacheInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }

    private Cache getCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), CACHE_PATH);
        return new Cache(httpCacheDirectory, CACHE_SIZE);
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Context context) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(context, CACHE_SIZE * 2))
                .build();

        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);

        return picasso;
    }
}
