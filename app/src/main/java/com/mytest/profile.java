package com.mytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;
import java.util.concurrent.Executor;

public class profile extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button Edit,Logout;
    TextView name,Gender,DOB,Contact,Email;
    FirebaseFirestore fstore;
    String USerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Logout=(Button)findViewById(R.id.logout);
        Edit=(Button) findViewById(R.id.EditProfile);
        mAuth = FirebaseAuth.getInstance();

        name = (TextView) findViewById(R.id.profile_name);
        Gender = (TextView)findViewById(R.id.Profie_gender);
        DOB = (TextView) findViewById(R.id.Profile_dateOfBirth);
        Contact = (TextView)findViewById(R.id.Profile_Contact);
        Email = (TextView)findViewById(R.id.Profile_Email);

        if(mAuth.getCurrentUser()== null){
            Intent i= new Intent(profile.this, login.class);
            startActivity(i);
            finish();
        }



        fstore= FirebaseFirestore.getInstance();
        USerID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        DocumentReference documentReference = fstore.collection("Users").document(USerID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                name.setText(documentSnapshot.getString("Name"));
                Gender.setText(documentSnapshot.getString("Gender"));
                DOB.setText(documentSnapshot.getString("Date_of_Birth"));
                Contact.setText(documentSnapshot.getString("Mobile_Number"));
                Email.setText(documentSnapshot.getString("Email"));


            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i= new Intent(profile.this, login.class);
                startActivity(i);
                finish();


            }
        });




    }
}
