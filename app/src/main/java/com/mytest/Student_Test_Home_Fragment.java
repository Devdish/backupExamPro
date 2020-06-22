package com.mytest;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mytest.tabs.PagerAdapter;
import com.mytest.tabs.Tab1;
import com.mytest.tabs.Tab2;
import com.mytest.tabs.Tab3;

public class Student_Test_Home_Fragment extends Fragment {


    View myFragment;

    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment= inflater.inflate(R.layout.student_test_home_fragment_page, container, false);
     viewPager= myFragment.findViewById(R.id.pager);
     tabLayout= myFragment.findViewById(R.id.TabLayout);



     return myFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setUpViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());

        adapter.addFragment(new Tab1(),"Test");
        adapter.addFragment(new Institute_Videos_Fragment(),"Video");
        adapter.addFragment(new Tab3(),"Books");
        viewPager.setAdapter(adapter);


    }


}
