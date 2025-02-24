//DemoActivity.java

//Dependencies
package com.example.lebitsoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//DemoActivity class
public class DemoActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make the activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Link to activity_demo.xml
        setContentView(R.layout.activity_demo);

        //Initializing objects in View
        Toolbar toolbar = findViewById(R.id.toolbar5);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setting up back button in toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back arrow
        }

        //Setup of back button to return to MainActivity
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(DemoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        //Button Click Listeners
        findViewById(R.id.btnHandRecognition).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnGlossingSASL).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnTextToAvatar).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnFeedback).setOnClickListener(view -> openUrl("https://www.google.com"));

        //On click for navigation back to MainActivity using bottom_nav_bar
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
}
