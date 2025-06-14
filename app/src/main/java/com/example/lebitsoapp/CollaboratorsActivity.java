package com.example.lebitsoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CollaboratorsActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the activity to fullscreen using a utility method
        Utility.TrueFullscreen(this);
        //Link the activity to its XML layout file
        setContentView(R.layout.activity_collaborators);

        //Initialize BottomNavigationView and Toolbar from layout
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar5);

        //Enable a back button in the top toolbar (for navigation)
        Utility.enableTopBarBackButton(this,toolbar,MainActivity.class);
        //Setup the bottom navigation bar with its behavior
        Utility.setupBottomNavigation(this,bottomNavigationView,MainActivity.class);

        //Set up button click listeners
        Buttons();
    }

    /**
     * Assigns click listeners to buttons and opens respective URLs when clicked.
     */
    private void Buttons() {
        findViewById(R.id.btn_ent1).setOnClickListener(view ->
                openUrl("https://www.google.com"));
        findViewById(R.id.btn_ent2).setOnClickListener(view ->
                openUrl("https://www.google.com"));
        findViewById(R.id.btn_ent3).setOnClickListener(view ->
                openUrl("https://www.google.com"));
        findViewById(R.id.btn_ent4).setOnClickListener(view ->
                openUrl("https://www.google.com"));
    }
    /**
     * Opens a URL in the device's default browser using an implicit intent.
     *
     * @param url The web address to open
     */
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

}