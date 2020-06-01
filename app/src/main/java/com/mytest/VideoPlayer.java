package com.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayer extends AppCompatActivity {


    TextView Title,Description, Date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_player);




        Title=(TextView)findViewById(R.id.Title_Player);
        Description= (TextView)findViewById(R.id.Description);
        Date=(TextView)findViewById(R.id.date_time);
       final String key= getIntent().getStringExtra("key");
        Title.setText(getIntent().getStringExtra("title"));
        Description.setText(getIntent().getStringExtra("description"));
        Date.setText(getIntent().getStringExtra("date"));


        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_views);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = key;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });


    }
}