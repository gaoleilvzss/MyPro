package com.example.mvpweather.presenter;

import com.example.mvpweather.model.WeatherRequest;
import com.example.mvpweather.utils.RetrofitUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V> {
    public V mvpView;
    public WeatherRequest weatherRequest;

    public CompositeSubscription compositeSubscription;
    public void attachView(V mvpView){
        this.mvpView = mvpView;
        weatherRequest = RetrofitUtils.getRetrofit().create(WeatherRequest.class);
    }

    public void detachView(){
        mvpView = null;
        if(compositeSubscription!=null&&compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }
    }

    public <T> void addSubciption(Observable<T> observable, Subscriber<T> subscriber){
        if(compositeSubscription==null){
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber));
    }

}
