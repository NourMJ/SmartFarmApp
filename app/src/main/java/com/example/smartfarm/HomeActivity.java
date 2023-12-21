package com.example.smartfarm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.smartfarm.Activities.Weather;
import com.example.smartfarm.databinding.ActivityHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityHomeBinding binding;
   Button BtnAjout,btnWeather;
   TextView temperature,humedity,Hsol;
   CardView CardSol;
   LinearLayout LayoutSol;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.seeWeather.setOnClickListener(this);
        binding.BtAjout.setOnClickListener(this);

        temperature=(TextView) findViewById(R.id.Temperature);
       // CardSol=(CardView) findViewById(R.id.card);
        humedity=(TextView) findViewById(R.id.Humididty);
        Hsol=(TextView) findViewById(R.id.HSol);
        LayoutSol=(LinearLayout) findViewById(R.id.Lsol);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
         DatabaseReference myRefH= database.getReference("humidity");
         DatabaseReference myRefT=database.getReference("temperature");
         DatabaseReference myRefSol=database.getReference("moisture");

         myRefH.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 float value = snapshot.getValue(Float.class);
                 String valeur =Float.toString(value);
                 humedity.setText(valeur);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(HomeActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

             }
         });
         myRefT.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 float value = snapshot.getValue(Float.class);
                 String valeur =Float.toString(value);
                 temperature.setText(valeur);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(HomeActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
             }
         });
         myRefSol.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 float value = snapshot.getValue(Float.class);
                 String valeur =Float.toString(value);
                 Hsol.setText(valeur);
                 if(value<40){

                    LayoutSol.setBackgroundColor(getResources().getColor(R.color.rouge,null));                 }
                 else {
                     LayoutSol.setBackgroundColor(getResources().getColor(R.color.white,null));
                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(HomeActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

             }
         });
         binding.acSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     Toast.makeText(HomeActivity.this, "Pompe activée", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     Toast.makeText(HomeActivity.this, "Pompe désactivée", Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }

    @Override
    public void onClick(View v) {
        if (v.equals(binding.seeWeather)){
            Intent intent =new Intent(HomeActivity.this, Weather.class);
            startActivity(intent);
        }
        if (v.equals(binding.BtAjout)){
            Intent intent =new Intent(HomeActivity.this,Note.class);
            startActivity(intent);
        }

    }

}