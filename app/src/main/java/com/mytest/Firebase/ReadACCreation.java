package com.mytest.Firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ReadACCreation extends Thread {
    String Email, Password;FirebaseAuth mauth;
Context Selected;
    public ReadACCreation(@Nullable Runnable target, String email, String password, Context selected) {
        super(target);
        Email = email;
        Password = password;
        Selected= selected;
     mauth=FirebaseAuth.getInstance();

     mauth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
         if(task.isSuccessful()){
             Toast.makeText(Selected, "User Created by External Class", Toast.LENGTH_SHORT).show();
         }
         else{
             Toast.makeText(Selected, "User Failed Created by External Class", Toast.LENGTH_SHORT).show();
         }
         }
     });


    }




}
