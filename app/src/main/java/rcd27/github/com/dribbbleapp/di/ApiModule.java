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
public class ApiModule {

    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTH = "Bearer d40d9ad2e7a946e27e922ac609b84ff86a91223585208473a821aa394c602003";
    private static final String BASE_ULR = "https://api.dribbble.com";

    private final File cacheDir;

    public ApiModule(File cacheDir) {
        this.cacheDir = cacheDir;
    }

    @Provides
    @Singleton
    public DribbbleApi getDribbleApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();

        HttpLoggingInterceptor networkLogger = new HttpLoggingInterceptor();
        networkLogger.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClientBuilder.addInterceptor(networkLogger);

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header(AUTHORIZATION, AUTH);
                //TODO FIXME: если отсутствует интернет, приложение не грузит
                //с диска, а просто валится.
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(cacheDir, cacheSize);
        httpClientBuilder.cache(cache);

        OkHttpClient client = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ULR)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(DribbbleApi.class);
    }
}
