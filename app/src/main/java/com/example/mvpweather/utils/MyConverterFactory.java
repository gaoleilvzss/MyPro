package com.example.mvpweather.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyConverterFactory extends Converter.Factory {
    private final Converter.Factory stringFactory = ScalarsConverterFactory.create();
    private final Converter.Factory gsonFactory = GsonConverterFactory.create();


    public static MyConverterFactory create(){
        return new MyConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation:annotations){
            if(!(annotation instanceof ResponseFormat)){
                continue;
            }
            String value  = ((ResponseFormat) annotation).value();
            if(ResponseFormat.GSON.equals(value)){
                return gsonFactory.responseBodyConverter(type,annotations,retrofit);
            }else{
                return stringFactory.responseBodyConverter(type,annotations,retrofit);
            }
        }
        return null;
    }
}
