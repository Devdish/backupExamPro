package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private  String userID;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        firebaseFirestore= FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mFirestoreList= findViewById(R.id.users_data);

        //--Query------------------//
        Query query=firebaseFirestore.collection("Users");

        //Recycler Options--------------------//
        FirestoreRecyclerOptions<adminModelforlist> options= new FirestoreRecyclerOptions.Builder<adminModelforlist>()
                .setQuery(query,adminModelforlist.class).build();
        //---adapter------------------------//

        adapter=new FirestoreRecyclerAdapter<adminModelforlist, Details.ProductViewHolder>(options) {


            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_registered_list,parent,false);

                return new ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull adminModelforlist model) {
                holder.name.setText(model.getName());
                holder.type.setText(model.getType_User());

            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);









    }


    class  ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView type;
        private LinearLayout clickes;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name_user);
            type= itemView.findViewById(R.id.type_user);
            clickes= itemView.findViewById(R.id.clickable);
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