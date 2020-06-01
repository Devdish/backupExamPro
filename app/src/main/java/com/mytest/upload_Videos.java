package com.mytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class upload_Videos extends AppCompatActivity {
EditText title,description,keyid;
Button upload;
FirebaseFirestore firebaseFirestore,DataB;
FirebaseAuth mAuth;
String namer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__videos);
    title= (EditText)findViewById(R.id.title_video);
    description=(EditText)findViewById(R.id.description_video);
    keyid=(EditText)findViewById(R.id.youtube_video_key);
    upload=(Button)findViewById(R.id.upload_now);
   DataB=FirebaseFirestore.getInstance();
    mAuth=FirebaseAuth.getInstance();

        String UserID=mAuth.getCurrentUser().getUid();



        DocumentReference documentReference= DataB.collection("Users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                assert documentSnapshot != null;
                namer = documentSnapshot.getString("Name");
            }
        });



    upload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String Title= title.getText().toString().trim();
            String Description= description.getText().toString().trim();
            String KeyID= keyid.getText().toString().trim();



            if(TextUtils.isEmpty(Title)){
                title.setError("Enter Title");

            }
            else if (TextUtils.isEmpty(Description)){
                description.setError("Endter Short Description");
            }
            else if(TextUtils.isEmpty(KeyID)){
                keyid.setError("Enter Key Here");
            }
            else{

                //.................................................................//


                //.................................................................//


                String uid,time;
                time=java.text.DateFormat.getDateTimeInstance().format(new Date());
                Map<String,Object> video_data= new HashMap<>();
                video_data.put("title",Title);
                video_data.put("description",Description);
                video_data.put("key",KeyID);
                video_data.put("date",time);
                //-----------------------------------------------------------------------//
                firebaseFirestore= FirebaseFirestore.getInstance();
                uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();


                firebaseFirestore.collection("Data").document(namer).collection("Videos").add(video_data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(upload_Videos.this, "Video Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(upload_Videos.this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                });






            }
        }
    });

    }
}