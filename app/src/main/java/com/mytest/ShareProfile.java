package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShareProfile extends AppCompatActivity {
    String UID;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_profile);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                String fullname,Email,Password,Contact,Gender,DOB,Categ,applydate,institute,about;

                fullname=getIntent().getStringExtra("fullname");
                Email=getIntent().getStringExtra("Email");
                Contact=getIntent().getStringExtra("Contact");
                Gender=getIntent().getStringExtra("Gender");
                DOB=getIntent().getStringExtra("DOB");
                Password=getIntent().getStringExtra("Password");
                Categ=getIntent().getStringExtra("Categ");
                applydate=getIntent().getStringExtra("applydate");
                institute=getIntent().getStringExtra("Institute");
                about=getIntent().getStringExtra("about");
                getuids();
                Log.d("UID Created", "onComplete:  ShareProfilePage "+UID);
        FirebaseFirestore Data= FirebaseFirestore.getInstance();
        Map<String,Object> data = new HashMap<>();
        data.put("Name",fullname);
        data.put("Email",Email);
        data.put("Mobile_Number",Contact);
        data.put("Gender",Gender);
        data.put("Date_of_Birth",DOB);
        data.put("Password",Password);
        data.put("Type_User","Student");
        data.put("Category",Categ);
        data.put("Join_Date",applydate);
        data.put("Institute",institute);
        data.put("Status","verified");
        data.put("About",about);
        data.put("UID",UID);
        Log.d("in fire checkkaro", "onComplete: "+UID+" "+getIntent().getStringExtra("fullname"));
        Data.collection("Users").document(UID).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ShareProfile.this,"Registration Successful",Toast.LENGTH_LONG).show();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//
                Toast.makeText(ShareProfile.this,"Data Not Sendded",Toast.LENGTH_LONG).show();
            }
        });
        Log.d("UID Created", "onComplete:  ShareProfilePage "+UID);

                Intent go = new Intent(ShareProfile.this,MainActivity.class);
                startActivity(go);
                finish();
            }
        },10000);

    }






    public void getuids(){
        mAuth= FirebaseAuth.getInstance();

        UID= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();


    }


//    private void TransData(final String Email, final String Password, String fullname, String Contact, String Gender, String DOB, String about, String Categ, String applydate, final String institute,String userIDS) {
//
//
//  }
}