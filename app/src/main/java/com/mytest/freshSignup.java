package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class freshSignup extends AppCompatActivity {
    EditText fname,email,contacts,dateofbirth,about;
    Button register;
    RadioGroup genderselect,type_user;
    RadioButton gender,userCat;

    FirebaseAuth mAuth;
    FirebaseFirestore DataB;
    String userID;
    ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_signup);

        mAuth= FirebaseAuth.getInstance();
        fname=(EditText)findViewById(R.id.fnameR);
        email=(EditText)findViewById(R.id.emailR);
        contacts=(EditText)findViewById(R.id.contactR);
        dateofbirth=(EditText)findViewById(R.id.dob);
        about=(EditText)findViewById(R.id.about);

        DataB= FirebaseFirestore.getInstance();
        register=(Button)findViewById(R.id.register);
        genderselect=(RadioGroup)findViewById(R.id.gendergroup);
        load=(ProgressBar)findViewById(R.id.progressBarSignup);

        type_user=(RadioGroup)findViewById(R.id.typegroup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mAuth.getCurrentUser() != null) {
                    Intent i = new Intent(freshSignup.this, controller.class);
                    startActivity(i);
                    finish();
                }


                int RadioID = genderselect.getCheckedRadioButtonId();
                gender = findViewById(RadioID);
//
//                final int userID = type_user.getCheckedRadioButtonId();
//                userCat=findViewById(userID);

                final String Gender = gender.getText().toString().trim();
                final String fullname = fname.getText().toString().trim();
                final String Email = email.getText().toString().trim();
                final String Contact = contacts.getText().toString().trim();
                final String DOB = dateofbirth.getText().toString().trim();
                final String About = about.getText().toString().trim();

//                final String TypeUser = userCat.getText().toString();
                if (TextUtils.isEmpty(fullname)) {
                    fname.setError("Enter Full Name");
                } else if (TextUtils.isEmpty(Email)) {
                    email.setError("Enter Email Address");
                } else if (TextUtils.isEmpty(Contact)) {
                    contacts.setError("Enter Mobile Number");
                } else if (Contact.length() < 10) {
                    contacts.setError("Enter Valid Contact Number");
                } else if (TextUtils.isEmpty(DOB)) {
                    dateofbirth.setError("Enter Date of Birth");
                } else if (TextUtils.isEmpty(About)) {
                    about.setError("Enter About Yourself");
                } else {
                    load.setVisibility(View.VISIBLE);
//                    FirebaseFirestore Datac = FirebaseFirestore.getInstance();
//                    Map<String, Object> data = new HashMap<>();
//                    data.put("Name", fullname);
//                    data.put("Email", Email);
//                    data.put("Mobile_Number", Contact);
//                    data.put("Gender", Gender);
//                    data.put("Date_of_Birth", DOB);
//                    data.put("About", About);
//                    data.put("Type_User", "Student");
//
//                    Datac.collection("Users").document().set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(freshSignup.this, "Regtration Successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(freshSignup.this, Choose_Institute.class);
                            i.putExtra("name",fullname);
                            i.putExtra("Email",Email);
                            i.putExtra("mobile",Contact);
                            i.putExtra("gender",Gender);
                            i.putExtra("dob",DOB);
                            i.putExtra("about",About);
                            i.putExtra("type_user","Student");

                            startActivity(i);
                            finish();
//                        }
//                    });
                }

            }
        });
    }
}
