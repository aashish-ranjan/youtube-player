package com.aashishranjan.youtubeplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "StandaloneActivity";
    private static final int STANDALONE_PLAYER_LAUNCH_REQUEST_CODE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);

        Button btnPlayVideo = (Button) findViewById(R.id.btnPlayVideo);
        Button btnPlayPlaylist = (Button) findViewById(R.id.btnPlayPlaylist);

        btnPlayVideo.setOnClickListener(this);
        btnPlayPlaylist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btnPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, YoutubeActivity.YOUTUBE_API_KEY, YoutubeActivity.YOUTUBE_VIDEO_ID
                    , 0, true, false);
                break;

            case R.id.btnPlayPlaylist:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YoutubeActivity.YOUTUBE_API_KEY, YoutubeActivity.YOUTUBE_PLAYLIST_ID,
                    0, 0, true, false);
                break;

            default:
        }

        try {
            if(intent != null) {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            YouTubeInitializationResult youTubeInitializationResult = YouTubeStandalonePlayer.getReturnedInitializationResult(intent);
            if (youTubeInitializationResult.isUserRecoverableError()) {
                youTubeInitializationResult.getErrorDialog(this, STANDALONE_PLAYER_LAUNCH_REQUEST_CODE).show();
            } else {
                String errorMessage = String.format("There was an error in initializing youtube standalone player (%s)", youTubeInitializationResult.toString());
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
            }
        }
    }
}
