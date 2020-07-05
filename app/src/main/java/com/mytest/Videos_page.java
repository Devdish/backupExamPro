package com.mytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Objects;

public class Videos_page extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private String userID,name;
    private FirebaseAuth mAuth;
    private VideoAdapter videoAdapter;
    private ArrayList<VideoModelClass> list;
    private Button add ;
    private String us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_page);


        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mFirestoreList = (RecyclerView) findViewById(R.id.youtube_list_recycler);
        userID= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        list = new ArrayList<>();
        videoAdapter =new VideoAdapter(this,list);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(videoAdapter);
        add= (Button)findViewById(R.id.upload_new_video);
        final String[] name = new String[1];
        final String[] type = new String[1];
        final String[] ins= new String[1];


//
//        Task<DocumentSnapshot> docReference= firebaseFirestore.collection("Users").document(userID).get();
        DocumentReference documentReference= firebaseFirestore.collection("Users").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                name[0] = documentSnapshot.getString("Name");
                type[0] =documentSnapshot.getString("Type_User");
                ins[0]= documentSnapshot.getString("Institute");


                Log.d("name Check", "onCreate: "+ name[0]);
                Log.d("TYPE  Check", "onCreate: "+ type[0]);

                if(type[0].equals("Institute")){
            add.setVisibility(View.VISIBLE);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Videos_page.this,upload_Videos.class));
                }
            });


               us=name[0];


                Log.d("check name Dishant", "onCreate: "+ us);
//        Toast.makeText(getContext(), "userID is "+ userID, Toast.LENGTH_SHORT).show();
                //--Query------------------//
                Query query = firebaseFirestore.collection("Data").document(us).collection("Videos");

                query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        ArrayList<VideoModelClass> lis = new ArrayList<>();
                        for (DocumentSnapshot d : queryDocumentSnapshots) {
                            VideoModelClass v = d.toObject(VideoModelClass.class);
                            lis.add(v);
                        }
                        list.addAll(lis);
                        videoAdapter.notifyDataSetChanged();
                    }
                });

            }

                else {
                    Query query = firebaseFirestore.collection("Data").document(ins[0]).collection("Videos");

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




                }
            }
        });







    }



}
