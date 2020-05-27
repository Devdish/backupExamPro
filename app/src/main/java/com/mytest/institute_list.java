package com.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class institute_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterRecycler adapterRecycler;
    private ArrayList<InsModal> insModalArrayList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_list);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        insModalArrayList = new ArrayList<>();
        adapterRecycler = new AdapterRecycler(this,insModalArrayList);
        recyclerView.setAdapter(adapterRecycler);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },5000);

        fetchData();
    }

    private void fetchData() {
        InsModal insModal = new InsModal("Himanshu","https://avatars0.githubusercontent.com/u/64833787?s=400&v=4");
        InsModal insModal1 = new InsModal("Dishu","https://avatars0.githubusercontent.com/u/64833787?s=400&v=4");
        insModalArrayList.add(insModal);
        insModalArrayList.add(insModal1);
        adapterRecycler.notifyDataSetChanged();
    }
}
