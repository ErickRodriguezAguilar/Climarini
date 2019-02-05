package com.a1133458.climarini.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1133458.climarini.API.RetrofitAPI;
import com.a1133458.climarini.API.RetrofitClient;
import com.a1133458.climarini.Adapter.WeatherAdapter;
import com.a1133458.climarini.Adapter.WeatherIcon;
import com.a1133458.climarini.Models.Result;
import com.a1133458.climarini.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    Result result;
    public static String baseURL = "http://api.openweathermap.org/";
    public static final String API_KEY="a8ac942256410d78c62571785e05a63a";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        initApiForHome(view);
        return view;
    }

    public void initRecyclerView(View view, Result result){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_day);
        WeatherAdapter adapter = new WeatherAdapter(view.getContext(), result);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    public void initApiForHome(final View view)  {
        RetrofitAPI api = RetrofitClient.getClient(baseURL).create(RetrofitAPI.class);
        Call<Result> call = api.getWeatherByCityName("mexicali",API_KEY);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response){
                Result result = response.body();
                initComponents(result, view);
                initRecyclerView(view,result);
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("tag",t.toString());
            }
        });
    }

    public void initComponents(Result result, View view){
        TextView locationtxt = (TextView) view.findViewById(R.id.ctiyname);
        TextView temperaturatxt = (TextView) view.findViewById(R.id.temp);
        TextView weather_conditiontxt = (TextView) view.findViewById(R.id.weather_condition);
        ImageView iconWeather = (ImageView) view.findViewById(R.id.imageWeather);

        String location = result.getCity().getName() +", "+result.getCity().getCountry();
        String temperatureC = ""+(Math.round(result.getList().get(0).getMain().getTemp() - 273.15))+"° C";
        String condition = result.getList().get(0).getWeather().get(0).getDescription();
        int iconID = new WeatherIcon().getIconId(result.getList().get(0).getWeather().get(0).getIcon());

        locationtxt.setText(location.toUpperCase());
        temperaturatxt.setText(temperatureC.toUpperCase());
        weather_conditiontxt.setText(condition.toUpperCase());
        iconWeather.setImageResource(iconID);

    }

}
