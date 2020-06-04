package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class student_home extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        mAuth= FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()== null){
            Intent i= new Intent(student_home.this, login.class);
            startActivity(i);
            finish();
        }


        BottomNavigationView bottomnav= findViewById(R.id.user_menu);

        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.student_home_Frame,new Student_Test_Home_Fragment()).commit();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment= null;
            switch (item.getItemId()){
                case  R.id.student_home:
                    selectedFragment= new Student_Test_Home_Fragment();
                    break;

                case  R.id.student_report:
                    selectedFragment= new Student_Result_Fragment();
                    break;

                case  R.id.student_Settings:
//                    selectedFragment= new Student_Video_Fragment();
                    selectedFragment= new Admin_setting_Fragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.student_home_Frame,selectedFragment).commit();
            return  true;

        }
    };

}
