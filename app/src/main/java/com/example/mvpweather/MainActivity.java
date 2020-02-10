package com.example.mvpweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpweather.model.City;
import com.example.mvpweather.model.County;
import com.example.mvpweather.model.Province;
import com.example.mvpweather.presenter.AreaPresenter;
import com.example.mvpweather.presenter.BingPicPresenter;
import com.example.mvpweather.view.AreaView;
import com.example.mvpweather.view.BingPicView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AreaView , BingPicView {
    public static final String TAG = "MainActivity";

    public int Level_pro = 0;
    public int Level_city = 1;
    public int Level_county = 2;

    public int current_level = 0;

    private Province seleProvince;
    private City seleCity;
    private County seleCounty;

    AreaPresenter areaPresenter = new AreaPresenter(this);
    BingPicPresenter bingPicPresenter = new BingPicPresenter(this);
    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList();
    private List<County> countyList = new ArrayList();

    private List<String> dataList = new ArrayList<>() ;

    private ListView listView;
    private TextView textView;
    private ImageView imageView;
    private Button button;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        textView = findViewById(R.id.title_text);
        button = findViewById(R.id.back_button);
        imageView = findViewById(R.id.bing_pic_img);
        bingPicPresenter.startGetBingPic();

        listView.setAdapter(arrayAdapter);
        textView.setText("China");
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(arrayAdapter);
        areaPresenter.startGetProvinceInfo();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_level==Level_county){
                    areaPresenter.startGetCityInfo(seleProvince.getId()+"");
                    current_level = Level_city;
                }else if(current_level == Level_city){
                    areaPresenter.startGetProvinceInfo();
                    current_level = Level_pro;
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(current_level == Level_pro){
                    seleProvince = provinceList.get(position);
                    areaPresenter.startGetCityInfo(seleProvince.getId()+"");
                    current_level = Level_city;
                }else if(current_level == Level_city){
                    seleCity = cityList.get(position);
                    areaPresenter.startCountyInfo(seleProvince.getId()+"",seleCity.getId()+"");
                    current_level = Level_county;
                }else if(current_level == Level_county){
                    seleCounty = countyList.get(position);
                    Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
                    intent.putExtra("weather_id",seleCounty.getWeatherId());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    @Override
    public void getProvinceSuccess(List<Province> provinces) {
        dataList.clear();
        provinceList.clear();
        provinceList = provinces;
        for (Province province : provinces){
            dataList.add(province.getProvinceName());
        }
        textView.setText("china");
        button.setVisibility(View.GONE);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCitySuccess(List<City> city) {
        dataList.clear();
        cityList.clear();
        cityList = city;
        for(City city1 : city){
            dataList.add(city1.getCityName());
        }
        textView.setText(seleProvince.getProvinceName());
        button.setVisibility(View.VISIBLE);
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void getCountySuccess(List<County> couty) {
        dataList.clear();
        countyList.clear();
        countyList = couty;
        for (County county:couty){
            dataList.add(county.getCountyName());
        }
        textView.setText(seleCity.getCityName());
        button.setVisibility(View.VISIBLE);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void getSuccessBingPicSrc(String picUrl) {
        Log.d(TAG, "getSuccessBingPicSrc: url = "+picUrl);
        if(picUrl!=null){
            Glide.with(this).load(picUrl).into(imageView);
        }
    }
}
