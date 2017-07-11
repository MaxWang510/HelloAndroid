package com.max.helloandroid.http;

import com.max.helloandroid.utils.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WangHuaGui on 2017/7/11 18:14
 */

public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 10;
    public final static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConstants.BaseUrl)
                .build();
        return retrofit.create(serviceClass);
    }
}
