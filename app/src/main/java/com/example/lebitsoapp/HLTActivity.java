//DeafResearchActivity.java
package com.example.lebitsoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.widget.ProgressBar;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HLTActivity extends AppCompatActivity {

    // Declare buttons and video view
    private VideoView videoView;

    public static final int REQUEST_CODE_VIDEO_PLAYBACK = 1;
    public static final int REQUEST_CODE_QUESTION = 2;
    private PlayerView playerView;
    ProgressBar progressBar;
    ImageView bt_fullscreen, top_banner, bottom_banner;
    GridView grid;
    SimpleExoPlayer simpleExoPlayer;

    boolean isFullScreen = false;
    LinearLayout sec_mid, sec_bottom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Link to xml layout file
        setContentView(R.layout.activity_human_language_technology);

        //Initializing objects in view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        playerView = findViewById(R.id.player);
        CardView playVideoButton = findViewById(R.id.btnPlay_Video);
        bt_fullscreen = findViewById(R.id.bt_fullscreen);
        Toolbar toolbar = findViewById(R.id.toolbar5);

        //Setting up back button on Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back arrow
        }

        //Home button in nav_menu to return to MainActivity
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(HLTActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
        //Setting up back button on Toolbar

        //Fullscreen method
        bt_fullscreen.setOnClickListener(v -> {
            if (!isFullScreen) {
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(HLTActivity.this, R.drawable.ic_fullscreen_exit));
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                isFullScreen = true;

            } else {
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(HLTActivity.this, R.drawable.ic_fullscreen_open));
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                isFullScreen = false;
            }
        });

        //Setting up exoplayer
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).setSeekBackIncrementMs(500).setSeekForwardIncrementMs(500).build();
        playerView.setPlayer(simpleExoPlayer);
        playerView.setKeepScreenOn(true);

        // Corrected video URI setup
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hlt_video);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        simpleExoPlayer.setMediaItem(mediaItem);
        simpleExoPlayer.prepare(); // Prepare the player
        simpleExoPlayer.setPlayWhenReady(true); // Ensures video starts playing

        //Setup toolbar navigation arrrow
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(HLTActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        //Button Click Listeners**
        findViewById(R.id.btnVideoPlaylist).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnDigitalResources).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnFeedback).setOnClickListener(view -> openUrl("https://www.google.com"));

        //
        playVideoButton.setOnClickListener(view -> { {
            simpleExoPlayer.seekTo(0); // Restart video if ended
            simpleExoPlayer.prepare();
        }
            simpleExoPlayer.setPlayWhenReady(true); // Start playing
        });

        //Set navigation for Bottom Navigation bar
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close DemoActivity
                return true;
            }
            return false;
        });


    }

    //Method for action of each Button Click Listener
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        simpleExoPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }
}
