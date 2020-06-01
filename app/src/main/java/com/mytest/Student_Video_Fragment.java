package com.mytest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Student_Video_Fragment extends Fragment {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mfirestorelist;
    private FirestoreRecyclerAdapter adapter;
    private FirebaseAuth mAuth;
    private  String uid,name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.student_videos_fragment_page, container, false);

        firebaseFirestore= FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();
        mfirestorelist= view.findViewById(R.id.student_youtube_list_recycler);

        uid= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        Toast.makeText(getContext(), "id is "+uid, Toast.LENGTH_SHORT).show();






     return  view;
    }

}
