//MainActivity

//Dependencies
package com.example.lebitsoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the activity full screen
        trueFullscreen();

        //Set view for activity
        setContentView(R.layout.activity_main);

        //Button Functions Section
        buttons();

    }

    private void trueFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void buttons() {
        //Introduction button
        findViewById(R.id.btnIntroduction).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IntroductionActivity.class);
            startActivity(intent);
        });
        //HLT button
        findViewById(R.id.btnHLT).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HLTActivity.class);
            startActivity(intent);
        });
        //Deaf Research button
        findViewById(R.id.btnDeafResearch).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DeafResearchActivity.class);
            startActivity(intent);
        });
        //Demo Button
        findViewById(R.id.btnDemo).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DemoActivity.class);
            startActivity(intent);
        });

        //Collaborators button
        findViewById(R.id.btnCollaborators).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.ufs.ac.za/icdf")); // Replace with your desired URL
            startActivity(intent);
        });
        //Feedback Button
        findViewById(R.id.btnFeedback).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.ufs.ac.za/icdf")); // Replace with your desired URL
            startActivity(intent);
        });
    }
}
