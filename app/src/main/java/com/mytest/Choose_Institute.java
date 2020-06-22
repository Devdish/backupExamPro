package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.grpc.CallCredentials;

public class Choose_Institute extends AppCompatActivity  {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private  String userID;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__institute);

        firebaseFirestore= FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mFirestoreList= findViewById(R.id.List_choose);

        //--Query------------------//
        Query query=firebaseFirestore.collection("Customers");

        //Recycler Options--------------------//
        FirestoreRecyclerOptions<ModelChoose> options= new FirestoreRecyclerOptions.Builder<ModelChoose>()
                .setQuery(query,ModelChoose.class).build();
        //---adapter------------------------//

        adapter=new FirestoreRecyclerAdapter<ModelChoose,ProductViewHolder>(options) {

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_type_institute,parent,false);

                return new  ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final ModelChoose model) {
                holder.name.setText(model.getName());
                holder.clicked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Choose_Institute.this,RegEndUser.class);
                       i.putExtra("Selected Institute", model.getName());
                       FirebaseFirestore databas= FirebaseFirestore.getInstance();
//                        userID= mAuth.getCurrentUser().getUid();
                        String ema= getIntent().getStringExtra("Email").toString();
                        Map<String,Object> selectInst = new HashMap<>();
                        selectInst.put("name",getIntent().getStringExtra("name"));
                        selectInst.put("email",getIntent().getStringExtra("Email"));
                        selectInst.put("mobile",getIntent().getStringExtra("mobile"));
                        selectInst.put("gender",getIntent().getStringExtra("gender"));
                        selectInst.put("date_of_birth",getIntent().getStringExtra("dob"));
                        selectInst.put("about",getIntent().getStringExtra("about"));
                        selectInst.put("type_user",getIntent().getStringExtra("type_user"));
                        selectInst.put("institute",model.getName());
                        selectInst.put("date", DateFormat.getDateTimeInstance().format(new Date()));
                        selectInst.put("status","not_verified");
                        databas.collection("Data").document(model.getName()).collection("users").document(ema).set(selectInst);
                        Toast.makeText(Choose_Institute.this,"Selected Institute is "+model.getName(),Toast.LENGTH_LONG).show();
                        startActivity(i);
                        finish();
                    }
                });
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }
    class  ProductViewHolder extends RecyclerView.ViewHolder{

     private TextView name;
     private LinearLayout clicked;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            clicked= itemView.findViewById(R.id.item_click);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}