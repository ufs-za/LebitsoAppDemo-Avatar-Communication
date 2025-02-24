//IntroductionActivity.java

//Dependencies

package com.example.lebitsoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.ProgressBar;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

//InductionActivity class
public class IntroductionActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_VIDEO_PLAYBACK = 1;
    public static final int REQUEST_CODE_QUESTION = 2;
    private PlayerView playerView;
    ProgressBar progressBar;
    ImageView bt_fullscreen;
    SimpleExoPlayer simpleExoPlayer;

    boolean isFullScreen = false;
    LinearLayout sec_mid, sec_bottom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        playerView = findViewById(R.id.player);
        bt_fullscreen = findViewById(R.id.bt_fullscreen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(IntroductionActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        bt_fullscreen.setOnClickListener(v -> {
            if (!isFullScreen) {
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(IntroductionActivity.this, R.drawable.ic_fullscreen_exit));
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                isFullScreen = true;
            } else {
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(IntroductionActivity.this, R.drawable.ic_fullscreen_open));
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                isFullScreen = false;
            }
        });

        simpleExoPlayer = new SimpleExoPlayer.Builder(this)
                .setSeekBackIncrementMs(500)
                .setSeekForwardIncrementMs(500)
                .build();
        playerView.setPlayer(simpleExoPlayer);
        playerView.setKeepScreenOn(true);

        // Corrected video URI setup
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introduction_video);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        simpleExoPlayer.setMediaItem(mediaItem);
        simpleExoPlayer.prepare(); // Prepare the player
        simpleExoPlayer.setPlayWhenReady(true); // Ensures video starts playing

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(IntroductionActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        //Navigation setup for
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

