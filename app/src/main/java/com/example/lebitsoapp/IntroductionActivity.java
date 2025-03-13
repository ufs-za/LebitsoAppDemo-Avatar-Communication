// Package declaration
package com.example.lebitsoapp;

// Import necessary Android and ExoPlayer libraries
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.ProgressBar;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

// IntroductionActivity class extends AppCompatActivity
public class IntroductionActivity extends AppCompatActivity {

    // Constants for request codes
    public static final int REQUEST_CODE_VIDEO_PLAYBACK = 1;
    public static final int REQUEST_CODE_QUESTION = 2;

    // UI elements and player instance
    private PlayerView playerView;
    ProgressBar progressBar;
    ImageView bt_fullscreen;
    SimpleExoPlayer simpleExoPlayer;

    BottomNavigationView bottomNavigationView;
    boolean isFullScreen = false;
    LinearLayout sec_mid, sec_bottom;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable fullscreen mode
        trueFullscreen();

        // Set the content view to the layout resource
        setContentView(R.layout.activity_introduction);

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        playerView = findViewById(R.id.player);
        bt_fullscreen = findViewById(R.id.bt_fullscreen);

        // Setup navigation and video player
        topBarBackBtn();
        screenOrientation();
        ExoPlayer();
        bottomNav();
    }

    // Method to configure bottom navigation bar behavior
    private void bottomNav() {
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                if (item.getItemId() == R.id.nav_home) {
                    // Navigate to MainActivity when home button is pressed
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
    // Method to handle toolbar back button behavior
    private void topBarBackBtn() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> {
            // Navigate back to MainActivity when back button is pressed
            Intent intent = new Intent(IntroductionActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    // Method to enable true fullscreen mode
    private void trueFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // Method to initialize and configure ExoPlayer for video playback
    private void ExoPlayer() {
        // Initialize ExoPlayer instance
        simpleExoPlayer = new SimpleExoPlayer.Builder(this)
                .setSeekBackIncrementMs(500)  // Set seek back increment
                .setSeekForwardIncrementMs(500) // Set seek forward increment
                .build();

        if (playerView != null) {
            playerView.setPlayer(simpleExoPlayer);
            playerView.setKeepScreenOn(true); // Keep screen on during playback
        }

        // Define video URI from raw resources
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introduction_video);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);

        // Set the media item and prepare the player
        simpleExoPlayer.setMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.setPlayWhenReady(true); // Auto-play video when ready
    }

    // Lifecycle method: Stop video playback when activity is stopped
    @Override
    protected void onStop() {
        super.onStop();
        simpleExoPlayer.stop();
    }

    // Lifecycle method: Release player resources when activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }
}