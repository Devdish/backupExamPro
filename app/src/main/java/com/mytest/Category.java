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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Category extends AppCompatActivity {
Button btn1;
FirebaseFirestore fstore;
FirebaseAuth mAuth;
String UserId;
private RecyclerView mrecyclerViewList;
 private  FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);



        mAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        mrecyclerViewList=(RecyclerView)findViewById(R.id.Institute_list_Recycler);




        //Query---------------------------------------------------------------------------->//

        Query query= fstore.collection("Institute");

        //Recycler Options==============================>//


        FirestoreRecyclerOptions<PreoductModel> options= new FirestoreRecyclerOptions.Builder<PreoductModel>()
                .setQuery(query,PreoductModel.class).build();
        // =======Adapter======================//
         adapter = new FirestoreRecyclerAdapter<PreoductModel, ProductViewHolder>(options) {
            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category_item, parent, false);
                return new ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull PreoductModel model) {
                holder.name.setText(model.getInstitute());
            }
        };

        mrecyclerViewList.setHasFixedSize(true);
        mrecyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerViewList.setAdapter(adapter);









        // ======================================================================================>//
    }

    private static class  ProductViewHolder extends RecyclerView.ViewHolder{
  private TextView name;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_list);

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
