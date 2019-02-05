package com.a1133458.climarini.API;

import com.a1133458.climarini.Models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {


    @GET("data/2.5/forecast")
    Call<Result> getWeatherByCityName(@Query("q") String cityname,
                                      @Query("appid") String API_KEY);
    @GET("data/2.5/forecast")
    Call<Result> getWeatherByZipCode(@Query("zip") int zipcode,
                                     @Query("appid") String API_KEY);

}

//http://api.openweathermap.org/data/2.5/forecast?q=seoul&appid=a8ac942256410d78c62571785e05a63a