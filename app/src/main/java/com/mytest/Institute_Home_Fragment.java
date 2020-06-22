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

public class Institute_Home_Fragment extends Fragment {


    LinearLayout create_test,test_report, books,videos,send_notification,students_doubts,More;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.institute_home_fragment_page, container, false);

        create_test= view.findViewById(R.id.create_exam);
        test_report= view.findViewById(R.id.test_reports);
        books= view.findViewById(R.id.books);
        videos= view.findViewById(R.id.videos_btn);
        send_notification= view.findViewById(R.id.sed_notification);
        students_doubts= view.findViewById(R.id.students_doubts);
        More= view.findViewById(R.id.more);


      videos.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getActivity(),Videos_page.class));
          }
      });

return  view;
    }

}
