package com.example.mvpweather.model;

import com.example.mvpweather.utils.ResponseFormat;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherRequest {


    @GET("china")
    @ResponseFormat("gson")
    Observable<List<Province>> getProvinceCall();
    @GET("china/{province}")
    @ResponseFormat("gson")
    Observable<List<City>> getCityCall(@Path("province")String province);
    @GET("china/{province}/{city}")
    @ResponseFormat("gson")
    Observable<List<County>> getCountyCall(@Path("province")String provinceId,@Path("city")String cityId);
    @GET("weather")
    @ResponseFormat("gson")
    Observable<WeatherModel> getWeatherCall(@Query("cityid")String weatherId,@Query("key")String key);
    @GET("bing_pic")
    @ResponseFormat("string")
    Observable<String> getBingPic();
}
