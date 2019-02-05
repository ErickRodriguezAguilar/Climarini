package com.a1133458.climarini.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1133458.climarini.Models.Result;
import com.a1133458.climarini.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    Result result;
    private Context context;
    private FragmentManager fragmentManager;


    public CardAdapter(Context context, Result result) {
        this.result = result;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_card_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        long temp =  Math.round(result.getList().get(position*8).getMain().getTemp() - 273.15);
        String temperatura ="" + temp+"° C";
        String datetime=  result.getList().get(position*8).getDtTxt().split(" ")[0];
        String dayName= this.getDateOfTheWeek(datetime).toUpperCase();
        String iconName =  result.getList().get(position*8).getWeather().get(0).getIcon();
        holder.cardcity.setText(result.getCity().getName() +", "+result.getCity().getCountry());
        holder.cardday.setText(dayName);
        holder.cardtemp.setText(temperatura);
        holder.iconWeather.setImageResource(new WeatherIcon().getIconId(result.getList().get(0).getWeather().get(0).getIcon()));
        initRecyclerView(holder.cardRecycler, result);

    }

    public String getDateOfTheWeek(String datetime){
        String dayName="";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        try {
            startDate = df.parse(datetime);
            return (new SimpleDateFormat("EEEE, dd").format(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datetime;
    }

    public void initRecyclerView(RecyclerView recycler, Result result){
        WeatherAdapter adapter = new WeatherAdapter(context, result);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cardcity;
        TextView cardday;
        TextView cardtemp;
        ImageView iconWeather;
        RelativeLayout parentLayout;
        RecyclerView cardRecycler;
        public ViewHolder(View itemView) {
            super(itemView);
            cardcity = itemView.findViewById(R.id.cardcity);
            cardday = itemView.findViewById(R.id.cardday);
            cardtemp = itemView.findViewById(R.id.cardtemp);
            iconWeather = itemView.findViewById(R.id.imageCard);
            cardRecycler = (RecyclerView) itemView.findViewById(R.id.cardrecycler);
        }


    }
}
