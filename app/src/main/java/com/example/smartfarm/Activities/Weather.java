package com.example.smartfarm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartfarm.Adapter.HourlyAdapters;
import com.example.smartfarm.Domain.Hourly;
import com.example.smartfarm.R;

import java.util.ArrayList;

public class Weather extends AppCompatActivity {
    private RecyclerView.Adapter adapterHorly;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initRecyclerview();
        setVariable();
    }

    private void setVariable() {

         TextView next7day=findViewById(R.id.next7day);
         next7day.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(Weather.this, FutureActivity.class);
                 startActivity(intent);
             }
         });

    }

    private void initRecyclerview() {
        ArrayList<Hourly> items=new ArrayList<>();

        items.add(new Hourly("9 pm",28,R.drawable.cloudy));
        items.add(new Hourly("11 pm",29,R.drawable.sunny));
        items.add(new Hourly("12 pm",30,R.drawable.windy));
        items.add(new Hourly("1 am",29,R.drawable.rainy));
        items.add(new Hourly("2 am",27,R.drawable.storm));

        recyclerview=findViewById(R.id.Rc1);
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterHorly=new HourlyAdapters(items);
        recyclerview.setAdapter(adapterHorly);
    }
}