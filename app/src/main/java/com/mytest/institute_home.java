package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class institute_home extends AppCompatActivity {
FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_home);

        mAuth= FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()== null){
            Intent i= new Intent(institute_home.this, login.class);
            startActivity(i);
            finish();
        }


        BottomNavigationView bottomnav= findViewById(R.id.insti_menu);

        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.institute_home_Frame,new Institute_Home_Fragment()).commit();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment= null;
            switch (item.getItemId()){
                case  R.id.institute_home:
                    selectedFragment= new Institute_Home_Fragment();
                    break;

                case  R.id.institute_videos:
                    selectedFragment= new Institute_Videos_Fragment();
                    break;

                case  R.id.institute_books:
                    selectedFragment= new Institute_Books_Fragment();
                    break;


                case  R.id.institute_students:
                    selectedFragment= new Institute_Users_Fragment();
                    break;


                case  R.id.institute_Settings:
                    selectedFragment= new Institute_Settings_Fragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.institute_home_Frame,selectedFragment).commit();
            return  true;

        }
    };

}
