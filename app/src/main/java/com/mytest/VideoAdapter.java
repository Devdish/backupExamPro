package com.mytest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context context,mcon;
    private ArrayList<VideoModelClass> list;


    public VideoAdapter(Context context, ArrayList<VideoModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_list_single_item, parent, false);
        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, int position) {
        final VideoModelClass modelClass = list.get(position);

        holder.title.setText(modelClass.getTitle());
        holder.date.setText(modelClass.getDate());
         String Key= modelClass.getKey();
         holder.clickes.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent j= new Intent(context,VideoPlayer.class);

                 j.putExtra("title",modelClass.getTitle());
                 j.putExtra("description",modelClass.getDescription());
                 j.putExtra("key",modelClass.getKey());
                 j.putExtra("date", modelClass.getDate());
                v.getContext().startActivity(j);

             }
         });


        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(modelClass.getKey(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView date;
        private LinearLayout clickes;
        private YouTubePlayerView youTubePlayerView;

//        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
//        getLifecycle().addObserver(youTubePlayerView);
//
//youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                String videoId = "S0Q4gqBUs7c";
//                youTubePlayer.loadVideo(videoId, 0);
//            }
//        });

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            youTubePlayerView = itemView.findViewById(R.id.youtube_player_view);
//            LifecycleOwner lifecycleOwner;

            title = itemView.findViewById(R.id.title_head);
            date = itemView.findViewById(R.id.time_head);
            clickes = itemView.findViewById(R.id.video_clickss);
        }
    }

}
