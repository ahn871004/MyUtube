package kr.co.ajsoft.myutube;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class ItemActivity extends YouTubeBaseActivity {

    YouTubePlayerView playerView;

    private final String VIDEO_CODE ="N0qPD9vUFbQ";
    private final String API_KEY ="AIzaSyDEs0WFaeOVUNsolGKBzHn5nRKU3ArYFcw";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        playerView=findViewById(R.id.youtube_player);

        Intent intent=getIntent();
        final String id=intent.getStringExtra("Id");

        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();



        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo(id);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                Toast.makeText(ItemActivity.this, youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
