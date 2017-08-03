package rcd27.github.com.dribbbleapp.model;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiModule {

    private static final String AUTH = "Bearer d40d9ad2e7a946e27e922ac609b84ff86a91223585208473a821aa394c602003";
    private static final String BASE_ULR = "https://api.dribbble.com";

    public static RetrofitRequest getDribbleApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor networkLogger = new HttpLoggingInterceptor();
        networkLogger.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClientBuilder.addInterceptor(networkLogger);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", AUTH);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ULR)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(RetrofitRequest.class);
    }
}
