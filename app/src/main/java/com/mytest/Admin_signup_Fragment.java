package com.mytest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Admin_signup_Fragment extends Fragment {
    EditText fname,email,contacts,dateofbirth,pass1,pass2;
    Button register;
    RadioGroup genderselect,type_user;
    RadioButton gender,userCat;

    FirebaseAuth mAuth;
    FirebaseFirestore DataB;
    String userID;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.admin_signup_fragment_page, container, false);
        mAuth= FirebaseAuth.getInstance();
        fname=(EditText)view.findViewById(R.id.fnameR);
        email=(EditText)view.findViewById(R.id.emailR);
        contacts=(EditText)view.findViewById(R.id.contactR);
        dateofbirth=(EditText)view.findViewById(R.id.dob);
        pass1=(EditText)view.findViewById(R.id.pass1);
        pass2=(EditText)view.findViewById(R.id.pass2);
        DataB= FirebaseFirestore.getInstance();
        register=(Button)view.findViewById(R.id.register);
        genderselect=(RadioGroup)view.findViewById(R.id.gendergroup);
        type_user=(RadioGroup)view.findViewById(R.id.typegroup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if(mAuth.getCurrentUser()!= null){
//                    Intent i= new Intent(signup.this, controller.class);
//                    startActivity(i);
//                    finish();
//                }



                int RadioID = genderselect.getCheckedRadioButtonId();
                gender= view.findViewById(RadioID);

                final int userID = type_user.getCheckedRadioButtonId();
                userCat=view.findViewById(userID);

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
                                Map<String,Object> data = new HashMap<>();
                                data.put("Name",fullname);
                                data.put("Email",Email);
                                data.put("Mobile_Number",Contact);
                                data.put("Gender",Gender);
                                data.put("Date_of_Birth",DOB);
                                data.put("Password",Password);
                                data.put("Type_User",TypeUser);
                                data.put("UID",userIDS);

                                Datac.collection("Users").document(userIDS).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity(),"Reg. Successful",Toast.LENGTH_LONG).show();

//                                        Intent i= new Intent(signup.this,controller.class);
//                                        startActivity(i);


                                    }
                                });


                                //Start-------------------//

                                if(TypeUser.equals("Institute")){
                                    FirebaseFirestore db= FirebaseFirestore.getInstance();
                                    Map<String,Object> dt= new HashMap<>();
                                    dt.put("name",fullname);
                                    db.collection("Customers").add(dt);

                                    FirebaseFirestore dbs=FirebaseFirestore.getInstance();
                                    Map<String,Object> inst= new HashMap<>();
                                    inst.put("Name",fullname);
                                    inst.put("uid",userIDS);

                                    dbs.collection("Data").document(fullname).set(inst);
                                }














                            }
                            else {
                                Toast.makeText(getActivity(),"Error Occure, Try Again",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });



return  view;

    }


}
