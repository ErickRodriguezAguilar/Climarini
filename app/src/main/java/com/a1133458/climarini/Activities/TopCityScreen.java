package com.a1133458.climarini.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.a1133458.climarini.API.RetrofitAPI;
import com.a1133458.climarini.API.RetrofitClient;
import com.a1133458.climarini.Adapter.CardAdapter;
import com.a1133458.climarini.Models.Result;
import com.a1133458.climarini.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopCityScreen extends AppCompatActivity {

    public static String baseURL = "http://api.openweathermap.org/";
    public static final String API_KEY="a8ac942256410d78c62571785e05a63a";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_city_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        this.setTitle("");
        Intent intent = getIntent();
        initApiForTopCity(getWindow().getDecorView().getRootView(), intent.getStringExtra("cityname"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initApiForTopCity(final View view, String cityname)  {
        RetrofitAPI api = RetrofitClient.getClient(baseURL).create(RetrofitAPI.class);
        Call<Result> call = api.getWeatherByCityName(cityname,API_KEY);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response){
                if(response.isSuccessful()) {
                    Log.e("code", "" + response.isSuccessful());
                    Result result = response.body();
                    initRecyclerView(view, result);
                }else{
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Error: Not connection with API Weather.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("tag",t.toString());
            }
        });
    }

    public void initRecyclerView(View view, Result result){
        recyclerView = view.findViewById(R.id.recyclerpickcity);
        CardAdapter adapter = new CardAdapter(view.getContext(), result);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }


}
