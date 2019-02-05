package com.a1133458.climarini.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
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

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    Result result;
    private Context context;
    private FragmentManager fragmentManager;


    public WeatherAdapter(Context context, Result result) {
        this.result = result;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_item,parent,false);
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

        holder.dayName.setText(dayName);
        holder.dayTemp.setText(temperatura);
        holder.iconWeather.setImageResource(new WeatherIcon().getIconId(iconName));

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

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dayName;
        TextView dayTemp;
        ImageView iconWeather;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.day);
            dayTemp = itemView.findViewById(R.id.temp);
            iconWeather = itemView.findViewById(R.id.image);
        }


    }

}
