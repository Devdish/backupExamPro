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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText Email,Password;
    Button loginbtn;
    TextView forgots;
    ProgressBar loginprog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email= (EditText)findViewById(R.id.emailL);
        Password=(EditText)findViewById(R.id.PassL);
        loginbtn=(Button)findViewById(R.id.loginNow);
        forgots=(TextView)findViewById(R.id.forgot);
        loginprog=(ProgressBar)findViewById(R.id.progressBarLogin);

        mAuth= FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!= null){
            Intent i= new Intent(login.this, controller.class);
            startActivity(i);
            finish();
        }



        forgots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(login.this,forgot_password.class);
                startActivity(j);
            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= Email.getText().toString().trim();
                String pass= Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    Email.setError("Enter Email Please");
                }
                else if (TextUtils.isEmpty(pass)){

                    Password.setError("Enter Password Please");
                }
                else {
                    loginprog.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(login.this,"Login Successful",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(login.this,controller.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(login.this,"Please Check details",Toast.LENGTH_LONG).show();
                            }


                        }
                    });


                }

            }
        });



    }
}
