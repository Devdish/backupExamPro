package com.mytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class controller extends AppCompatActivity {
FirebaseAuth mAuth;
FirebaseFirestore DataB;
String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

         DataB= FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();

        UserID= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        DocumentReference documentReference= DataB.collection("Users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                String TypeUser=documentSnapshot.getString("Type_User");
                Log.d("HomeUID", "onCreate: "+ TypeUser);
                if(TypeUser.equals("Admin")){

                    if(mAuth.getCurrentUser()!= null){
                        Intent i= new Intent(controller.this, admin_home.class);
                        startActivity(i);
                        finish();
                    }
                }
                else  if(TypeUser.equals("Institute")){

                    if(mAuth.getCurrentUser()!= null){
                        Intent i= new Intent(controller.this, institute_home.class);
                        startActivity(i);
                        finish();
                    }

                }
                else if(TypeUser.equals("Student")){

                    if(mAuth.getCurrentUser()!= null){
                        Intent i= new Intent(controller.this, student_home.class);
                        startActivity(i);
                        finish();
                    }



                }
                else if(TypeUser.equals(null)){

                    if(mAuth.getCurrentUser()!= null){
                        Intent i= new Intent(controller.this, student_home.class);
                        startActivity(i);
                        finish();
                    }



                }
                else {

                    if (mAuth.getCurrentUser() != null) {
                        Intent i = new Intent(controller.this, student_home.class);
                        startActivity(i);
                        finish();
                    }
                }

                }
        });








    }
}
