package com.a1133458.climarini.Fragments;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1133458.climarini.Adapter.TopAdapter;
import com.a1133458.climarini.Adapter.WeatherAdapter;
import com.a1133458.climarini.R;
import java.util.ArrayList;

public class TopFragment extends Fragment {

    private ArrayList<String> topCities = new ArrayList<String>();
    Application app ;

    public TopFragment(Application app){
        this.app=app;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_top,null);
        initArrayString();
        initRecyclerView(view);
        return view;
    }

    public void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclertop);
        TopAdapter adapter = new TopAdapter(view.getContext(), topCities, app);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public  void initArrayString(){
        topCities.add("Tokyo, JP");
        topCities.add("London, GB");
        topCities.add("Seoul, KR");
        topCities.add("Sydney, AU");
        topCities.add("Tijuana, MX");
        topCities.add("Berlin, DE");
        topCities.add("Barcelona, ES");
        topCities.add("Phoenix, US");
        topCities.add("Toronto, CA");
        topCities.add("Hong Kong, CN");
    }


}
