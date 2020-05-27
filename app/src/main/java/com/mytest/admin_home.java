package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class admin_home extends AppCompatActivity {

    Fragment layoutfrag;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        mAuth= FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()== null){
            Intent i= new Intent(admin_home.this, login.class);
            startActivity(i);
            finish();
        }


        BottomNavigationView bottomnav= findViewById(R.id.admin_menu);

        bottomnav.setOnNavigationItemSelectedListener(navListener);

  getSupportFragmentManager().beginTransaction().replace(R.id.admin_home_Frame,new Admin_Home_Fragment()).commit();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

      Fragment selectedFragment= null;
      switch (item.getItemId()){
          case  R.id.admin_home:
              selectedFragment= new Admin_Home_Fragment();
              break;

            case  R.id.admin_user:
            selectedFragment= new Admin_signup_Fragment();
            break;

         case  R.id.admin_Settings:
        selectedFragment= new Admin_setting_Fragment();
              break;
    }

    getSupportFragmentManager().beginTransaction().replace(R.id.admin_home_Frame,selectedFragment).commit();
      return  true;

        }
    };

}
