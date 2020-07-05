package com.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mytest.uploadTest.UploadQuestion;

public class QuestionAdded extends AppCompatActivity {
int j;
String Titles,Categorys,Institutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_added);

        Categorys= getIntent().getStringExtra("Category");
        Titles= getIntent().getStringExtra("Title");
        Institutes= getIntent().getStringExtra("Institute");
        j= Integer.parseInt(getIntent().getStringExtra("num"));
        j-=1;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                if (j>0) {
//                    Intent go = new Intent(QuestionAdded.this, UploadQuestion.class);
//                    go.putExtra("num", j);
//                    go.putExtra("title", Titles);
//                    go.putExtra("Category", Categorys);
//                    go.putExtra("Institute", Institutes);
//                    startActivity(go);
//                    finish();
//                }
//                else {
//                    Intent go = new Intent(QuestionAdded.this, MainActivity.class);
//                }

            }
        }, 5000);

    }


}
