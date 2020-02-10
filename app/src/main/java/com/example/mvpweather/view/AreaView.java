package com.example.mvpweather.view;

import com.example.mvpweather.model.City;
import com.example.mvpweather.model.County;
import com.example.mvpweather.model.Province;

import java.util.List;

public interface AreaView {
    void getProvinceSuccess(List<Province> province);
    void getCitySuccess(List<City> city);
    void getCountySuccess(List<County> couty);
}
