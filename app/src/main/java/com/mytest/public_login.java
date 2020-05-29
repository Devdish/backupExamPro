package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class public_login extends AppCompatActivity {

    EditText fname,email,contacts,dateofbirth,pass1,pass2;
    Button register;
    RadioGroup genderselect,type_user;
    RadioButton gender,userCat;

    FirebaseAuth mAuth;
    FirebaseFirestore DataB,db;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_login);
        mAuth= FirebaseAuth.getInstance();
        fname=(EditText)findViewById(R.id.fnameR);
        email=(EditText)findViewById(R.id.emailR);
        contacts=(EditText)findViewById(R.id.contactR);
        dateofbirth=(EditText)findViewById(R.id.dob);
        pass1=(EditText)findViewById(R.id.pass1);
        pass2=(EditText)findViewById(R.id.pass2);
        DataB= FirebaseFirestore.getInstance();

        register=(Button)findViewById(R.id.register);
        genderselect=(RadioGroup)findViewById(R.id.gendergroup);
        type_user=(RadioGroup)findViewById(R.id.typegroup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if(mAuth.getCurrentUser()!= null){
//                    Intent i= new Intent(signup.this, controller.class);
//                    startActivity(i);
//                    finish();
//                }



                int RadioID = genderselect.getCheckedRadioButtonId();
                gender= findViewById(RadioID);

                final int userID = type_user.getCheckedRadioButtonId();
                userCat=findViewById(userID);

                final String Gender = gender.getText().toString().trim();
                final String fullname = fname.getText().toString().trim();
                final String Email = email.getText().toString().trim();
                final String Contact = contacts.getText().toString().trim();
                final String DOB = dateofbirth.getText().toString().trim();
                final String Password = pass1.getText().toString().trim();
                final String PasswordConf = pass2.getText().toString().trim();
                final String TypeUser = userCat.getText().toString();
                if(TextUtils.isEmpty(fullname)){
                    fname.setError("Enter Full Name");
                }
                else if(TextUtils.isEmpty(Email)){
                    email.setError("Enter Email Address");
                }

                else if(TextUtils.isEmpty(Contact)){
                    contacts.setError("Enter Mobile Number");
                }

                else if(Contact.length()<10){
                    contacts.setError("Enter Valid Contact Number");
                }

                else if(TextUtils.isEmpty(DOB)){
                    dateofbirth.setError("Enter Date of Birth");
                }

                else if(TextUtils.isEmpty(Password)){
                    pass1.setError("Enter Password");
                }

                else if((Password.length())<8){
                    pass1.setError("Password Should be More than 8 Character");
                }
                else if(TextUtils.isEmpty(PasswordConf)){
                    pass2.setError("Enter password again");
                }
                else if(!Password.equals(PasswordConf)){
                    pass2.setError("Password don't match");
                }
                else {

                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String userIDS;
                                userIDS= mAuth.getCurrentUser().getUid();
                                FirebaseFirestore Datac= FirebaseFirestore.getInstance();
                            Map<String, Object> dis = new HashMap<>();
                            dis.put("name", fullname);
                            Datac.collection("Customers").add(dis);
                            Map<String, Object> data = new HashMap<>();
                            data.put("Name", fullname);
                            data.put("Email", Email);
                            data.put("Mobile Number", Contact);
                            data.put("Gender", Gender);
                            data.put("Date of Birth", DOB);
                            data.put("Password", Password);
                            data.put("Type User", TypeUser);
                            data.put("UID", userIDS);
                            data.put("name", fullname);


                            Datac.collection("Users").document(userIDS).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(public_login.this, "Account Created Successful", Toast.LENGTH_LONG).show();
                                }
//                                        Intent i= new Intent(signup.this,controller.class);
//                                        startActivity(i);

                            });
                            }
                            else {
                                Toast.makeText(public_login.this,"Error Occure, Try Again",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });





    }


}
