package com.example.mvpweather.view;

import com.example.mvpweather.model.WeatherModel;

public interface WeatherView {
    void getSuccess(WeatherModel weatherModel);
}
