package com.a1133458.climarini.API;

import android.content.Context;


public class RetrofitController {

    public static final int BY_CITYNAME=1;
    public static final int BY_CODE=2;


    public static Context context;
    public static String baseURL = "http://api.openweathermap.org/";
    public static final String API_KEY="a8ac942256410d78c62571785e05a63a";

    public RetrofitController(Context context){
        this.context=context;
    }


}
