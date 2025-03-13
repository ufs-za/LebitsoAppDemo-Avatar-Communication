package com.example.lebitsoapp;

// Import necessary Android and ExoPlayer dependencies
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeafResearchActivity extends AppCompatActivity {

    // UI components
    private PlayerView playerView;
    private ProgressBar progressBar;
    private ImageView bt_fullscreen;
    private SimpleExoPlayer simpleExoPlayer;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private CardView playVideoButton;

    // Boolean to track fullscreen state
    private boolean isFullScreen = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable full-screen mode
        trueFullscreen();

        // Link this activity to its XML layout file
        setContentView(R.layout.activity_sasl4_irdata);

        // Initialize UI components by finding views from the XML layout
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar5);
        playVideoButton = findViewById(R.id.btnPlay_Video);
        playerView = findViewById(R.id.player);
        bt_fullscreen = findViewById(R.id.bt_fullscreen);

        // Set up various UI and functionality components
        topBarBackBtn(); // Configure the toolbar back button
        screenOrientation(); // Handle fullscreen mode toggle
        ExoPlayer(); // Initialize and configure the ExoPlayer for video playback
        Buttons(); // Set up button click listeners
        bottomNav(); // Configure bottom navigation behavior
    }

    // Set up the toolbar's back button to return to MainActivity
    private void topBarBackBtn() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(DeafResearchActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    // Configure bottom navigation behavior
    private void bottomNav() {
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                if (item.getItemId() == R.id.nav_home) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            });
        }
    }

    // Set up button click actions
    private void Buttons() {
        findViewById(R.id.btnVideoPlaylist).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnDigitalResources).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnFeedback).setOnClickListener(view -> openUrl("https://www.google.com"));

        if (playVideoButton != null) {
            playVideoButton.setOnClickListener(view -> {
                // Restart and play the video when the button is clicked
                simpleExoPlayer.seekTo(0);
                simpleExoPlayer.prepare();
                simpleExoPlayer.setPlayWhenReady(true);
            });
        }
    }

    // Enable full-screen mode for the activity
    private void trueFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // Handle screen orientation changes when clicking the fullscreen button
    private void screenOrientation() {
        if (bt_fullscreen != null) {
            bt_fullscreen.setOnClickListener(v -> {
                if (!isFullScreen) {
                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fullscreen_exit));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                } else {
                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fullscreen_open));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                isFullScreen = !isFullScreen;
            });
        }
    }

    // Initialize and configure ExoPlayer for video playback
    private void ExoPlayer() {
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).setSeekBackIncrementMs(500).setSeekForwardIncrementMs(500).build();
        if (playerView != null) {
            playerView.setPlayer(simpleExoPlayer);
            playerView.setKeepScreenOn(true);
        }

        // Load a local video resource
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introduction_video);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        simpleExoPlayer.setMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.setPlayWhenReady(true);
    }

    // Open a URL in a web browser
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // Pause video playback when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.pause();
        }
    }

    // Stop video playback when activity is stopped
    @Override
    protected void onStop() {
        super.onStop();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
        }
    }

    // Release ExoPlayer resources when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }
}
