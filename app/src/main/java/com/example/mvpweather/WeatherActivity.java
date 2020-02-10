package com.example.mvpweather;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpweather.model.WeatherModel;
import com.example.mvpweather.presenter.WeatherPresenter;
import com.example.mvpweather.utils.RetrofitUtils;
import com.example.mvpweather.view.WeatherView;

public class WeatherActivity extends AppCompatActivity implements WeatherView {
    public static final  String TAG = "WeatherActivity";
    private String weather_id;
    WeatherPresenter weatherPresenter = new WeatherPresenter(this);
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weather_id = getIntent().getStringExtra("weather_id");
        textView = findViewById(R.id.weather_text);
        weatherPresenter.startGetWeatherRequest(weather_id, RetrofitUtils.KEY);
    }

    @Override
    public void getSuccess(WeatherModel weatherModel) {
        Log.d(TAG, "getSuccess: weatherModel = "+weatherModel.toString());
    }
}
