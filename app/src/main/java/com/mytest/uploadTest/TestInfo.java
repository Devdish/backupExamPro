package com.mytest.uploadTest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mytest.R;
import com.mytest.TimerCreated.TimerCoundown;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestInfo extends AppCompatActivity {

    EditText Title,Description,category,Marks,Questions,Passing,Durations;
    Button next;
    FirebaseFirestore fstore;
    String namer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);
      Title=(EditText)findViewById(R.id.Heading);
      Description=(EditText)findViewById(R.id.DescriptionTest);
      category=(EditText)findViewById(R.id.CategoryTest);
      Marks=(EditText)findViewById(R.id.MarksTest);
      Questions=(EditText)findViewById(R.id.QuestionsTest);
      Passing=(EditText)findViewById(R.id.PassingMarksTest);
      Durations=(EditText)findViewById(R.id.timeTest);
      next=(Button)findViewById(R.id.nextprocess);
      fstore= FirebaseFirestore.getInstance();
String UserID= FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference= fstore.collection("Users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                assert documentSnapshot != null;
                namer = documentSnapshot.getString("Name");
            }
        });

       final String  time=java.text.DateFormat.getDateTimeInstance().format(new Date());
        next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final String Titles= Title.getText().toString();
              String Descriptions=Description.getText().toString();
              final String Categorys=category.getText().toString();
              String Markss=Marks.getText().toString();
              final String Question=Questions.getText().toString();
              String Passings=Passing.getText().toString();
              String Duration=Durations.getText().toString();
              if(Titles.isEmpty()){
                  Title.setError("Please Enter Title of Test");
              }
              else if (Descriptions.isEmpty()){
                  Description.setError("Please Enter Description");
              }
              else if (Categorys.isEmpty()){
                  category.setError("Please Enter Category");
              }
              else if (Markss.isEmpty()){
                  Marks.setError("Please Enter Total Marks");
              }
              else if (Question.isEmpty()){
                  Questions.setError("Please Enter No. of Questions");
              }
              else if (Passings.isEmpty()){
                  Passing.setError("Please Enter Passing Marks");
              }
              else if (Duration.isEmpty()){
                  Durations.setError("Please Enter Time Limit");
              }
              else {
                  Map<String,Object> Quiz= new HashMap<>();
                  Quiz.put("Title", Titles);
                  Quiz.put("Description",Descriptions);
                  Quiz.put("Category",Categorys);
                  Quiz.put("Marks",Markss);
                  Quiz.put("Questions",Question);
                  Quiz.put("Passing",Passings);
                  Quiz.put("Time",Duration);
                  Quiz.put("Date", time);
                  fstore.collection("Data").document(namer).collection("Test")
                          .document(Categorys).collection(Titles).document("Quiz").set(Quiz).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(TestInfo.this, "Test Created Now Add Questions", Toast.LENGTH_SHORT).show();
                          Intent i= new Intent(TestInfo.this,UploadQuestion.class);
                          i.putExtra("Title",Titles);
                              i.putExtra("Category",Categorys);
                              i.putExtra("Institute",namer);
                              i.putExtra("numQuestion",Question);
                              startActivity(i);
                          }
                          else {
                              Toast.makeText(TestInfo.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                          }
                      }
                  });
              }
          }
      });
    }
}