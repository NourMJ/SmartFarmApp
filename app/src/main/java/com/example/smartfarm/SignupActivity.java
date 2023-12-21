package com.example.smartfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText SignupName,SignupEmail,SignupUsername,SignupPassword;
    TextView RedirectText;
    Button signupButton;
    FirebaseDatabase Database;
    DatabaseReference Reference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignupName=findViewById(R.id.sign_name);
        SignupEmail=findViewById(R.id.sign_email);
        SignupUsername=findViewById(R.id.user_name);
        SignupPassword=findViewById(R.id.signup_password);
        signupButton=findViewById(R.id.btn_signup);
        RedirectText=findViewById(R.id.LoginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database=FirebaseDatabase.getInstance();
                Reference=Database.getReference("users");
                String name= SignupName.getText().toString();
                String email= SignupEmail.getText().toString();
                String username= SignupUsername.getText().toString();
                String password= SignupPassword.getText().toString();
                HelperClass helperClass=new HelperClass(name,email,username,password);
                Reference.child(username).setValue(helperClass);

               Toast.makeText(SignupActivity.this,"you have signup successful",Toast.LENGTH_LONG).show();
              Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
              startActivity(intent);


            }
        });
        RedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });



    }
}