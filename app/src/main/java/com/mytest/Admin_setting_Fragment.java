package com.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;
import java.util.concurrent.Executor;

public class Admin_setting_Fragment extends Fragment {



    FirebaseAuth mAuth;
    Button profile,Logout;
    FirebaseFirestore fstore;
    String USerID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.admin_settings_fragment_page, container, false);

        //------------------------------------------------------------------
        Logout = (Button) view.findViewById(R.id.logout);
        profile = (Button) view.findViewById(R.id.Profile);
        mAuth = FirebaseAuth.getInstance();


        fstore= FirebaseFirestore.getInstance();
        if (mAuth.getCurrentUser() == null) {
            Intent i = new Intent(getActivity(), login.class);
            startActivity(i);
            requireActivity().finish();
        }

                Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i= new Intent(getActivity(), login.class);
                startActivity(i);
                requireActivity().finish();


                     }
                });


            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent j= new Intent(getActivity(),profile.class);
                    startActivity(j);
                }
            });





        return  view;
    }

}
