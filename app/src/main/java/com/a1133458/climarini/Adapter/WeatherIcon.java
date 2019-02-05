package com.a1133458.climarini.Adapter;

import com.a1133458.climarini.R;

public class WeatherIcon {

    public int getIconId(String iconCode){
        switch(iconCode){
            case "01d": return R.drawable.day01;
            case "02d": return R.drawable.day02;
            case "03d": return R.drawable.day03;
            case "04d": return R.drawable.day04;
            case "09d": return R.drawable.day09;
            case "10d": return R.drawable.day10;
            case "11d": return R.drawable.day11;
            case "13d": return R.drawable.day13;
            case "50d": return R.drawable.day50;
            case "01n": return R.drawable.night01;
            case "02n": return R.drawable.night02;
            case "03n": return R.drawable.night03;
            case "04n": return R.drawable.night04;
            case "09n": return R.drawable.night09;
            case "10n": return R.drawable.night10;
            case "11n": return R.drawable.night11;
            case "13n": return R.drawable.night13;
            case "50n": return R.drawable.night50;
        }
        return 0;
    }
}
