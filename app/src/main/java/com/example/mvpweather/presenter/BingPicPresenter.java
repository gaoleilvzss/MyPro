package com.example.mvpweather.presenter;

import com.example.mvpweather.view.BingPicView;

import rx.Subscriber;

public class BingPicPresenter extends BasePresenter<BingPicView> {
    public BingPicPresenter(BingPicView bingPicView){
        attachView(bingPicView);
    }

    public void startGetBingPic(){
        addSubciption(weatherRequest.getBingPic(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                mvpView.getSuccessBingPicSrc(s);
            }
        });
    }
}
