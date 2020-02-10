package com.example.mvpweather.presenter;

import com.example.mvpweather.model.WeatherModel;
import com.example.mvpweather.view.WeatherView;

import rx.Subscriber;

public class WeatherPresenter extends BasePresenter<WeatherView> {
    public WeatherPresenter(WeatherView weatherView){
        attachView(weatherView);
    }

    public void startGetWeatherRequest(String cityId,String key){
        addSubciption(weatherRequest.getWeatherCall(cityId, key), new Subscriber<WeatherModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WeatherModel weatherModel) {
                mvpView.getSuccess(weatherModel);
            }
        });
    }
}
