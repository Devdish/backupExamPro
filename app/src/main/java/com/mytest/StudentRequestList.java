package com.mytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentRequestList extends AppCompatActivity {
 private RecyclerView Request_List;
 private FirebaseFirestore firebaseFirestore;
 private FirebaseAuth mAuth;
 private SudentRequestAdapter sudentRequestAdapter;
 private ArrayList<StudentListModelClass> list;
public String name_of_institute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request_list);
        firebaseFirestore= FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();
        list= new ArrayList<>();
        sudentRequestAdapter= new SudentRequestAdapter(this,list);

        Request_List= findViewById(R.id.list_request_student);
        Request_List.setLayoutManager(new LinearLayoutManager(StudentRequestList.this));
        Request_List.setAdapter(sudentRequestAdapter);
        Request_List.setHasFixedSize(true);
//=================================================================================================//

        String UserID= mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                name_of_institute= documentSnapshot.getString("Name");







        //Query
        Query query= firebaseFirestore.collection("Data").document(name_of_institute).collection("users");

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                ArrayList<StudentListModelClass> lis= new ArrayList<>();
                assert queryDocumentSnapshots != null;
                for(DocumentSnapshot d: queryDocumentSnapshots){
                    StudentListModelClass v=d.toObject(StudentListModelClass.class);
                    lis.add(v);

                }
                list.addAll(lis);
              sudentRequestAdapter.notifyDataSetChanged();



            }
        });


            }
        });



    }





}