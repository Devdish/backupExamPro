package com.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_setting_Fragment extends Fragment {

    FirebaseAuth mAuth;
    Button Edit,Logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.admin_settings_fragment_page, container, false);

        //------------------------------------------------------------------
        Logout =(Button) view.findViewById(R.id.logout);
         Edit = (Button) view.findViewById(R.id.EditProfile);
        mAuth= FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()== null){
            Intent i= new Intent(getActivity(), login.class);
            startActivity(i);
//            finish();
        }


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i= new Intent(getActivity(), login.class);
                startActivity(i);
//                finish();


            }
        });


            //------------------------------------------------------------------


//        Logout=(Button)findViewById(R.id.logout);
//        Edit=(Button) findViewById(R.id.EditProfile);
//
//
//
//        if(mAuth.getCurrentUser()== null){
//            Intent i= new Intent(profile.this, login.class);
//            startActivity(i);
//            finish();
//        }
//
//
//        Logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent i= new Intent(profile.this, login.class);
//                startActivity(i);
//                finish();
//
//
//            }
//        });



        return  view;
    }

}
