package com.example.mvpweather.utils;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static final String BASE_URL = "http://guolin.tech/api/";
    public static final String KEY = "bc0418b57b2d4918819d3974ac1285d9";
    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("gaolei","retrofitBack = "+message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitUtils.BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(MyConverterFactory.create()).client(client).build();
        return retrofit;
    }

}
