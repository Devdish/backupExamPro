package com.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity

{


    FirebaseAuth mAuth;
    Button login,sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mAuth=FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser()!= null){
            Intent i= new Intent(MainActivity.this, controller.class);
            startActivity(i);
            finish();
        }


        login= (Button) findViewById(R.id.login);
        sign=(Button) findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, login.class));
            }
        });


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, freshSignup.class));
            }
        });


    }
}
