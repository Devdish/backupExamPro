package com.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button Edit,Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Logout=(Button)findViewById(R.id.logout);
        Edit=(Button) findViewById(R.id.EditProfile);

        mAuth= FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()== null){
            Intent i= new Intent(profile.this, login.class);
            startActivity(i);
            finish();
        }


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i= new Intent(profile.this, login.class);
                startActivity(i);
                finish();


            }
        });




    }
}
