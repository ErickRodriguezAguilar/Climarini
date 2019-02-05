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
import android.widget.SearchView;
import android.widget.Toast;

import com.a1133458.climarini.API.RetrofitAPI;
import com.a1133458.climarini.API.RetrofitClient;
import com.a1133458.climarini.Adapter.CardAdapter;
import com.a1133458.climarini.Models.Result;
import com.a1133458.climarini.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    public static String baseURL = "http://api.openweathermap.org/";
    public static final String API_KEY="a8ac942256410d78c62571785e05a63a";
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,null);
        recyclerView = view.findViewById(R.id.recyclercard);
        initComponents(view);
        return  view;
    }

    public void initComponents(final View view){
        final SearchView searchView = (SearchView) view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView.setVisibility(View.VISIBLE);
                    initApiForSearch(view,query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.setVisibility(View.GONE);
                return false;
            }
        });
    }

    public void initApiForSearch(final View view,String cityname)  {
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
                    Toast.makeText(getContext().getApplicationContext(), "Error: Not city found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("tag",t.toString());
            }
        });
    }

    public void initRecyclerView(View view, Result result){
        //RecyclerView recyclerView = view.findViewById(R.id.recyclercard);
        CardAdapter adapter = new CardAdapter(view.getContext(), result);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}



