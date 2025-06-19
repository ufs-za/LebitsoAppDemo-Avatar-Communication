package com.example.lebitsoapp;

//Dependencies

import android.annotation.SuppressLint;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeafResearchActivity extends AppCompatActivity {

    private PlayerView playerView;
    private ImageView bt_fullscreen;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private CardView playVideoButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TrueFullScreen
        Utility.TrueFullscreen(this);

        //Link to XML layout file
        setContentView(R.layout.activity_sasl4_irdata);

        //Initialize UI components
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar5);
        playVideoButton = findViewById(R.id.btnPlay_Video);
        playerView = findViewById(R.id.player);
        bt_fullscreen = findViewById(R.id.bt_fullscreen);

        //Call utility methods to setup navigation for toolbar and navbar
        Utility.enableTopBarBackButton(this,toolbar,MainActivity.class);
        Utility.setupBottomNavigation(this,bottomNavigationView,MainActivity.class);

        //Call videoplayeractivity to allow for initialization of exoplayer and handling
        //screen orientation of player
        VideoPlayerActivity.initializePlayer(this, playerView, "android.resource://"
                + getPackageName() + "/" + R.raw.deaf_research_video);
        VideoPlayerActivity.handleScreenOrientation(this, bt_fullscreen);

        //Initialize button method
        Buttons();
    }

    //Buttons Method
    private void Buttons() {
        findViewById(R.id.btnVideoPlaylist).setOnClickListener(view ->
                openUrl("https://www.google.com"));
        findViewById(R.id.btnDigitalResources).setOnClickListener(view -> {
            Intent intent = new Intent(this, DRLinksActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btnFeedback).setOnClickListener(view ->
                openUrl("https://forms.office.com/r/WgFPnHfv6y"));

        if (playVideoButton != null) {
            playVideoButton.setOnClickListener(view -> {
                VideoPlayerActivity.onPlay();
            });
        }
    }

    //Method for action of each Button Click Listener
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    //Lifecycle helpers
    @Override
    protected void onPause() {
        super.onPause();
        VideoPlayerActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoPlayerActivity.onDestroy();
    }
    //Lifecycle helpers
}