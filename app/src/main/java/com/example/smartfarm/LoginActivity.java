package com.example.smartfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText loginusername,loginPassword;
    Button loginButton;
    TextView SignupRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginusername=findViewById(R.id.login_username);
        loginPassword=findViewById(R.id.login_password);
        loginButton=findViewById(R.id.btn_login);
        SignupRedirectText=findViewById(R.id.RedirectLOgin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateusername()| !validateuPassword()){

                }else{
                    checkUser();
                    Intent intent =new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
        SignupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean validateusername(){
        String val=loginusername.getText().toString();
        if(val.isEmpty())
        {
            loginusername.setError("user name can not be empty ");
            return false;
        }

        else{
            loginusername.setError(null);
            return true;
        }

    }
    public boolean validateuPassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String Username=loginusername.getText().toString().trim();
        String Userpassword=loginPassword.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Log.d("Debug", "checkUser: Username - " + Username);
        Query checkUserDatabase = reference.orderByChild("username").equalTo(Username);


        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginusername.setError(null);
                    String passwordFromDB = snapshot.child(Username).child("password").getValue(String.class);

                    if (Objects.equals(passwordFromDB, Userpassword)) {
                        // Passwords match, start MainActivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Passwords do not match, show error
                        loginPassword.setError("Invalid password");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginusername.setError("User does not exist");
                    loginusername.requestFocus();
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}