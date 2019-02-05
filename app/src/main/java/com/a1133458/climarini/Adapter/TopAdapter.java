package com.a1133458.climarini.Adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a1133458.climarini.Activities.AppScreen;
import com.a1133458.climarini.Activities.TopCityScreen;
import com.a1133458.climarini.R;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{

    ArrayList<String> topCities;
    private Context context;
    private FragmentManager fragmentManager;
    Application app;

    public TopAdapter(Context context, ArrayList<String> topcities, Application app) {
        this.topCities = topcities;
        this.context = context;
        this.app=app;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_top_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.topCity.setText(topCities.get(position));
        holder.numCity.setText(""+(position+1));
        holder.cardTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(app.getApplicationContext(), TopCityScreen.class);
                intent.putExtra("cityname",topCities.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView topCity;
        TextView numCity;
        CardView cardTop;
        public ViewHolder(View itemView) {
            super(itemView);
            cardTop = itemView.findViewById(R.id.card_topcity);
            topCity = itemView.findViewById(R.id.top_city);
            numCity = itemView.findViewById(R.id.numero_top);
        }


    }

}

