package com.mytest.uploadTest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mytest.QuestionAdded;
import com.mytest.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UploadQuestion extends AppCompatActivity {
EditText QN,Question,Opt1,Opt2,Opt3,Opt4,Correct;
Button addNow;
Handler gettings,detss;
String QuestNo,Quest,Op1,Op2,Op3,Op4,Crt;
String ttl,inst,cats,QueNumm;
int l,j,ck=0;
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

        gettings= new Handler(getApplicationContext().getMainLooper());
        detss=new Handler(getApplicationContext().getMainLooper());
      final String Categorys,Titles,Institutes,NumQuestions;
     Categorys= getIntent().getStringExtra("Category");
     Titles= getIntent().getStringExtra("Title");
     Institutes= getIntent().getStringExtra("Institute");
     NumQuestions= getIntent().getStringExtra("numQuestion");
    j = Integer.parseInt(NumQuestions);

     addNow.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {





             





             //Thread---------------------------------------------------------
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     gettings.post(new Runnable() {
                         @Override
                         public void run() {
                             QuestNo=QN.getText().toString();
                             Quest=Question.getText().toString();
                             Op1=Opt1.getText().toString();
                             Op2=Opt2.getText().toString();
                             Op3=Opt3.getText().toString();
                             Op4=Opt4.getText().toString();
                             Crt=Correct.getText().toString();
                         }
                     });

                     detss.post(new Runnable() {
                         @Override
                         public void run() {
                        ttl= Titles;
                        inst= Institutes;
                        cats= Categorys;
                        QueNumm=NumQuestions;
                         }
                     });
                     Log.d("Dtad", "run:title "+ttl+" Institute: "+inst+" catagory: "+cats+" Quest Nums: "+QueNumm);
                     Log.d("testing Values", "question Number: "+QuestNo+" Question: "+Quest+" op1: "+Op1+" op2: "+Op2+" op3: "+Op3+" op4: "+Op4+" Cort: "+Crt);
                     FirebaseFirestore fstore= FirebaseFirestore.getInstance();
                     Map<String,Object> Quiz= new HashMap<>();
                     Quiz.put("Q_No", QuestNo);
                     Quiz.put("Question",Quest);
                     Quiz.put("Option1",Op1);
                     Quiz.put("Option2",Op2);
                     Quiz.put("Option3",Op3);
                     Quiz.put("Option4",Op4);
                     Quiz.put("Correct",Crt);
                     fstore.collection("Data").document(inst).collection("Test")
                             .document(cats).collection(ttl).document(QuestNo).set(Quiz).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(UploadQuestion.this, "Question Added Successfully", Toast.LENGTH_SHORT).show();
//                                 Intent i= new Intent(UploadQuestion.this,UploadQuestion.class);
//                                 i.putExtra("Title",Titles);
//                                 i.putExtra("Category",Categorys);
//                                 i.putExtra("Institute",Institutes);
//                                 i.putExtra("num",NumQuestions);
//                                 startActivity(i);
                             }
                             else {
                                 Toast.makeText(UploadQuestion.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }
             }).start();
//----------------------Thread End---------------------------------------------------------------------------








//
//              if(ck==0) {
//                      ck=1;
//                  upQuest(j);
//                  Intent f = new Intent(UploadQuestion.this, QuestionAdded.class);
//                  f.putExtra("num", j);
//                  f.putExtra("title", Titles);
//                  f.putExtra("Category", Categorys);
//                  f.putExtra("Institute", Institutes);
//                  startActivity(f);
//              }
//              else {
//                  ck=1;
//
//                  upQuest(j);
//                  Intent f = new Intent(UploadQuestion.this, QuestionAdded.class);
//                  f.putExtra("num", j);
//                  f.putExtra("title", Titles);
//                  f.putExtra("Category", Categorys);
//                  f.putExtra("Institute", Institutes);
//                  startActivity(f);
//
//
//                  l = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("num")));
//
//              }

              }
     });
    }
    public void upQuest(int i){
        Toast.makeText(this, "Question Added "+i, Toast.LENGTH_SHORT).show();
    }
}
