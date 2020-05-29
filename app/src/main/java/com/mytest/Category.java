package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class Category extends AppCompatActivity {

private     FirebaseFirestore fstore;
 private    FirebaseAuth mAuth;

 private    RecyclerView mrecyclerViewList;
  private   FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        mrecyclerViewList = findViewById(R.id.Institute_list_Recycler);




        //Query---------------------------------------------------------------------------->//

        Query query= fstore.collection("members");

        //Recycler Options==============================>//


        FirestoreRecyclerOptions<ModelForCat> options= new FirestoreRecyclerOptions.Builder<ModelForCat>()
                .setQuery(query,ModelForCat.class).build();
        // =======Adapter======================//
         adapter = new FirestoreRecyclerAdapter<ModelForCat, ProductViewHolder>(options) {


             @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category_item, parent, false);
                return new ProductViewHolder(view);
            }

             @Override
             protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ModelForCat model) {
                 holder.item_lists.setText(model.getName());
             }

        };

        mrecyclerViewList.setHasFixedSize(true);
        mrecyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerViewList.setAdapter(adapter);


        // ======================================================================================>//
    }

    private static class  ProductViewHolder extends RecyclerView.ViewHolder{
  public TextView item_lists;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            item_lists = itemView.findViewById(R.id.item_lists);

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

   //===============================================================================================================//

















    }

