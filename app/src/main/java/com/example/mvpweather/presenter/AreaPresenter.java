package com.example.mvpweather.presenter;

import android.util.Log;

import com.example.mvpweather.model.City;
import com.example.mvpweather.model.County;
import com.example.mvpweather.model.Province;
import com.example.mvpweather.view.AreaView;

import java.util.List;

import rx.Subscriber;

public class AreaPresenter extends BasePresenter<AreaView> {
    public AreaPresenter(AreaView areaView){
        attachView(areaView);
    }
    public void startCountyInfo(String provinceId,String cityId){
        addSubciption(weatherRequest.getCountyCall(provinceId, cityId), new Subscriber<List<County>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<County> counties) {
                mvpView.getCountySuccess(counties);
            }
        });
    }
    public void startGetCityInfo(String provinceId){
        addSubciption(weatherRequest.getCityCall(provinceId), new Subscriber<List<City>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<City> cities) {
                mvpView.getCitySuccess(cities);
            }
        });
    }
    public void startGetProvinceInfo(){
        addSubciption(weatherRequest.getProvinceCall(), new Subscriber<List<Province>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Province> provinces) {
                mvpView.getProvinceSuccess(provinces);
            }
        });
    }
}
