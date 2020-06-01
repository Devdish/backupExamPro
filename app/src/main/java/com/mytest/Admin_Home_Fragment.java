package com.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Admin_Home_Fragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private String userID;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_home_fragment_page, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mFirestoreList = view.findViewById(R.id.Registered_Accounts);

        //--Query------------------//
        Query query = firebaseFirestore.collection("Users");

        //Recycler Options--------------------//
        FirestoreRecyclerOptions<adminModelforlist> options = new FirestoreRecyclerOptions.Builder<adminModelforlist>()
                .setQuery(query, adminModelforlist.class).build();
        //---adapter------------------------//

        adapter = new FirestoreRecyclerAdapter<adminModelforlist, ProductViewHolder>(options) {


            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_registered_list,parent,false);
                return new ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final adminModelforlist model) {

                holder.name.setText(model.getName());
                holder.type.setText(model.getType_User());
                holder.clickes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),ShowToAdmin.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("gender",model.getGender());
                        intent.putExtra("email",model.getEmail());
                        intent.putExtra("uid",model.getUID());
                        intent.putExtra("dob",model.getDate_of_Birth());
                        intent.putExtra("mobile",model.getMobile_Number());
                        intent.putExtra("User Type",model.getType_User());
                        intent.putExtra("password",model.getPassword());
                        intent.putExtra("institute",model.getInstitute());
                        startActivity(intent);
                    }
                });
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFirestoreList.setAdapter(adapter);

  return  view;
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView type;
        private LinearLayout clickes;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_user);
            type = itemView.findViewById(R.id.type_user);
            clickes = itemView.findViewById(R.id.clickable);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}