package com.mytest.uploadTest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mytest.QuestionAdded;
import com.mytest.R;

import java.util.Objects;

public class UploadQuestion extends AppCompatActivity {
EditText QN,Question,Opt1,Opt2,Opt3,Opt4,Correct;
Button addNow;
int l,j,ck=0;
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_question);

        QN=(EditText) findViewById(R.id.QuestNo);
        Question=(EditText)findViewById(R.id.Question);
        Opt1=(EditText)findViewById(R.id.op1);
        Opt2=(EditText)findViewById(R.id.op2);
        Opt3=(EditText)findViewById(R.id.op3);
        Opt4=(EditText)findViewById(R.id.op4);
        Correct=(EditText)findViewById(R.id.correct);
        addNow=(Button)findViewById(R.id.addQ);
     final String Categorys,Titles,Institutes,NumQuestions;
     Categorys= getIntent().getStringExtra("Category");
     Titles= getIntent().getStringExtra("Title");
     Institutes= getIntent().getStringExtra("Institute");
     NumQuestions= getIntent().getStringExtra("numQuestion");
    j = Integer.parseInt(NumQuestions);

     addNow.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
              if(ck==0) {
                      ck=1;
                  upQuest(j);
                  Intent f = new Intent(UploadQuestion.this, QuestionAdded.class);
                  f.putExtra("num", j);
                  f.putExtra("title", Titles);
                  f.putExtra("Category", Categorys);
                  f.putExtra("Institute", Institutes);
                  startActivity(f);
              }
              else {
                  ck=1;

                  upQuest(j);
                  Intent f = new Intent(UploadQuestion.this, QuestionAdded.class);
                  f.putExtra("num", j);
                  f.putExtra("title", Titles);
                  f.putExtra("Category", Categorys);
                  f.putExtra("Institute", Institutes);
                  startActivity(f);


                  l = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("num")));

              }

              }
     });
    }
    public void upQuest(int i){
        Toast.makeText(this, "Question Added "+i, Toast.LENGTH_SHORT).show();
    }
}
