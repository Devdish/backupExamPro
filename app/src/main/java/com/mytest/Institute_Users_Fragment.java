package com.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Institute_Users_Fragment extends Fragment {

    LinearLayout Request, Students;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.institute_users_fragment_page, container, false);

        Request=view.findViewById(R.id.request);
        Students= view.findViewById(R.id.studentss);

        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(getContext(),StudentRequestList.class);
                startActivity(j);
            }
        });

        Students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(getContext(),RegisteredStudentList.class);
                startActivity(j);
            }
        });

      return  view;
    }

}
