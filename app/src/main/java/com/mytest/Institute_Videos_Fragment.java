package com.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;

public class Institute_Videos_Fragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private String userID,name;
    private FirebaseAuth mAuth;
    private VideoAdapter videoAdapter;
    private ArrayList<VideoModelClass> list;
    private  Button add ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.institute_videos_fragment_page, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mFirestoreList = view.findViewById(R.id.youtube_list_recycler);
        userID= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        list = new ArrayList<>();
        videoAdapter =new VideoAdapter(requireContext(),list);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(requireContext()));
        mFirestoreList.setAdapter(videoAdapter);
       add= view.findViewById(R.id.upload_new_video);



//
       Task<DocumentSnapshot> documentReference= firebaseFirestore.collection("Users").document(userID).get();

//        Toast.makeText(getContext(), "userID is "+ userID, Toast.LENGTH_SHORT).show();
        //--Query------------------//
        Query query = firebaseFirestore.collection("Data").document("Aaksh Academy").collection("Videos");

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<VideoModelClass> lis = new ArrayList<>();
                for (DocumentSnapshot d : queryDocumentSnapshots) {
                    VideoModelClass v = d.toObject(VideoModelClass.class);
                    lis.add(v);
                    Log.d("gfahfgadh", v.getDate());
                }
                list.addAll(lis);
                videoAdapter.notifyDataSetChanged();
                Log.d("gfahfgadh", list.size() + "");
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),upload_Videos.class));            }
        });
        return  view;
    }



}
