//DemoActivity.java

//Dependencies
package com.example.lebitsoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//DemoActivity class
public class DemoActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make the activity full screen
        Utility.TrueFullscreen(this);

        //Link to activity_demo.xml
        setContentView(R.layout.activity_demo);

        //Initializing objects in View
        toolbar = findViewById(R.id.toolbar5);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Call utility methods to setup navigation for toolbar and navbar
        Utility.enableTopBarBackButton(this,toolbar,MainActivity.class);
        Utility.setupBottomNavigation(this,bottomNavigationView,MainActivity.class);

        //Initialize button method
        Buttons();

    }

    /**
     * Assigns click listeners to buttons and opens respective URLs when clicked.
     */
    private void Buttons() {
        //Button Click Listeners
        findViewById(R.id.btnHandRecognition).setOnClickListener(view -> openUrl("https://blank-app-ks1s6ypypfp.streamlit.app/"));
        findViewById(R.id.btnGlossingSASL).setOnClickListener(view -> openUrl("https://b6fkttf5wpfbw4rmg7xram.streamlit.app/"));
        findViewById(R.id.btnTextToAvatar).setOnClickListener(view -> openUrl("https://sign.mt/"));
        findViewById(R.id.btnFeedback).setOnClickListener(view -> openUrl("https://forms.office.com/r/WgFPnHfv6y"));
    }

    //Method for action of each Button Click Listener
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
