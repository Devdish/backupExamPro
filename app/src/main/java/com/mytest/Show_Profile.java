package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mytest.Firebase.ReadACCreation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Show_Profile extends AppCompatActivity {

    EditText fname,email,contacts,dateofbirth,pass1,pass2, about, Categ,applydate;
    Button register;
    RadioGroup genderselect,type_user;
    RadioButton gender,userCat;
    TextView institute, typeStu;
    FirebaseAuth mAuth;
    FirebaseFirestore DataB;
    String userID;
    ProgressBar load;
    String statuss;
    String UID;
    ReadACCreation creating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__profile);

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
        load=(ProgressBar)findViewById(R.id.progressBarSignup);
        about=(EditText)findViewById(R.id.abouts);
        Categ=(EditText)findViewById(R.id.Cate);
        applydate=(EditText)findViewById(R.id.applyDate);
        institute=(TextView)findViewById(R.id.insName);
        typeStu = (TextView) findViewById(R.id.type);
        type_user=(RadioGroup)findViewById(R.id.typegroup);

        fname.setText(getIntent().getStringExtra("Name"));
        email.setText(getIntent().getStringExtra("Email"));
        contacts.setText(getIntent().getStringExtra("Mobile"));
        dateofbirth.setText(getIntent().getStringExtra("DOB"));
        about.setText(getIntent().getStringExtra("About"));
        applydate.setText(getIntent().getStringExtra("Date"));
        institute.setText(getIntent().getStringExtra("Istitute"));
        typeStu.setText(getIntent().getStringExtra("Type_User"));
        statuss= getIntent().getStringExtra("Status");
        int RadioID = genderselect.getCheckedRadioButtonId();
        gender= findViewById(RadioID);
        gender.setText(getIntent().getStringExtra("Gender"));
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mAuth.getCurrentUser()!= null){
//                    Intent i= new Intent(Show_Profile.this, controller.class);
//                    startActivity(i);
//                    finish();
//                }
//                final int userID = type_user.getCheckedRadioButtonId();
//                userCat=findViewById(userID);
               final String Gender = gender.getText().toString().trim();
               final String fullname = fname.getText().toString().trim();
//                final String Email = email.getIntent().getStringExtra("Email");

                final String Email = email.getText().toString().trim();
                final String Contact = contacts.getText().toString().trim();
                final String DOB = dateofbirth.getText().toString().trim();
                final String Password = pass1.getText().toString().trim();

                String PasswordConf = pass2.getText().toString().trim();
//                final String TypeUser = userCat.getText().toString();
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
                    load.setVisibility(View.VISIBLE);
                    //========================================================================================

                   CreateAccount(Email,Password,fullname,Contact,Gender,DOB,about.getText().toString(),Categ.getText().toString(),applydate.getText().toString(),institute.getText().toString());


                            //                    TransData(Email,Password,fullname,Contact,Gender,DOB,about.getText().toString(),Categ.getText().toString(),applydate.getText().toString(),institute.getText().toString());
                    //=========================================================================================
                }
            }
        });
    }



    private void CreateAccount(final String Email, final String Password, String fullname, String Contact, String Gender, String DOB, String about, String Categ, String applydate, final String institute) {


//        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(Show_Profile.this,"Account Created",Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(Show_Profile.this,"Error Occurs, Try Again",Toast.LENGTH_LONG).show();
//                }
//            }
//        });


        creating= new ReadACCreation(null, Email,Password,Show_Profile.this);




   String userIDS= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Intent i= new Intent(Show_Profile.this,ShareProfile.class);
        i.putExtra("Email",Email);
        i.putExtra("Password",Password);
        i.putExtra("Institute",institute);
        i.putExtra("fullname",fullname);
        i.putExtra("Contact",Contact);
        i.putExtra("Gender",Gender);
        i.putExtra("DOB",DOB);
        i.putExtra("about",about);
        i.putExtra("Categ",Categ);
        i.putExtra("applydate",applydate);
        startActivity(i);
        Log.d("UID Created", "onComplete:  ShowProfilePage "+userIDS);
        DocumentReference ref= FirebaseFirestore.getInstance().collection("Data").document(institute)
                .collection("users").document(Email);
        ref.delete();
        finish();

    }


}
