package com.aashishranjan.youtubeplayer;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YoutubeActivity";
    public static final String YOUTUBE_API_KEY = "AIzaSyBZEH0wwwfCCCk0tgOhWQWohxNfPpxYU0M";
    public static final String YOUTUBE_VIDEO_ID = "ElpitAfkRS4";
    public static final String YOUTUBE_PLAYLIST_ID = "PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
//        ConstraintLayout playerLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);
        ConstraintLayout playerLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(playerLayout);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        playerView.initialize(YOUTUBE_API_KEY, this);
        playerLayout.addView(playerView);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is " + provider.getClass().toString());
        Toast.makeText(this, "YouTube Player initialized successfully", Toast.LENGTH_SHORT).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        if (!wasRestored) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error in initializing the YouTube Player (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this, "Video is playing", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "Video has paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this, "Video has stopped", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "Click Ad now and make the video creator rich!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this, "Nice! video is playing ok", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this, "Congratulations! You've completed another video", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
