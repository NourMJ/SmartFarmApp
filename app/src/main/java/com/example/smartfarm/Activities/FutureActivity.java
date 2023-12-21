package com.example.smartfarm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartfarm.Adapter.FutureAdapters;
import com.example.smartfarm.Domain.FutureDemain;
import com.example.smartfarm.HomeActivity;
import com.example.smartfarm.R;

import java.util.ArrayList;

public class FutureActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTomorrow;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future);
        initRecyclerview();
        setVariable();
    }

    private void setVariable() {
        ImageView backBtn=findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FutureActivity.this, Weather.class);
                startActivity(intent);
            }
        });

    }

    private void initRecyclerview() {
        ArrayList<FutureDemain> items=new ArrayList<>();

        items.add(new FutureDemain("sat",R.drawable.storm,"storm",25,10));
        items.add(new FutureDemain("Sun",R.drawable.cloudy,"cloudy",24,16));
        items.add(new FutureDemain("Mon",R.drawable.windy,"windy",29,15));
        items.add(new FutureDemain("Tue",R.drawable.cloudy_sunny,"cloudy_sunny",22,13));
        items.add(new FutureDemain("Wen",R.drawable.sunny,"Sun",28,11));
        items.add(new FutureDemain("Thu",R.drawable.rainy,"rainy",23,12));
        recyclerView=findViewById(R.id.Rc2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapterTomorrow= new FutureAdapters(items);
        recyclerView.setAdapter(adapterTomorrow);



    }
}