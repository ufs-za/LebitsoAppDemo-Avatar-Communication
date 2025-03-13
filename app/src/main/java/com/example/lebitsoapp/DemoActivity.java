// Package declaration
package com.example.lebitsoapp;

// Import necessary Android and support libraries
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * DemoActivity is an activity that demonstrates UI navigation and button interactions.
 * It includes:
 * - Fullscreen mode
 * - A top navigation bar with a back button
 * - A bottom navigation bar for switching activities
 * - Buttons that open external URLs
 */
public class DemoActivity extends AppCompatActivity {

    // UI components
    private BottomNavigationView bottomNavigationView;  // Bottom navigation menu
    private Toolbar toolbar;  // Toolbar (top action bar)

    /**
     * Called when the activity is first created.
     * @param savedInstanceState Saved state of the activity (if applicable)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable full-screen mode
        trueFullscreen();

        // Set the activity layout from activity_demo.xml
        setContentView(R.layout.activity_demo);

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar5);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up UI interactions
        Buttons();
        topBarBackBtn();
        bottomNav();
    }

    /**
     * Configures the bottom navigation bar and its item click listener.
     * - Clicking "Home" navigates back to MainActivity.
     */
    private void bottomNav() {
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                if (item.getItemId() == R.id.nav_home) {
                    // Navigate back to MainActivity and clear the activity stack
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // Close this activity
                    return true;
                }
                return false;
            });
        }
    }

    /**
     * Enables full-screen mode by hiding the title bar and using fullscreen window flags.
     */
    private void trueFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Enable fullscreen mode
    }

    /**
     * Configures the toolbar (top action bar) to include a back button that navigates to MainActivity.
     */
    private void topBarBackBtn() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set up the back button click listener
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(DemoActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // Close this activity
        });
    }

    /**
     * Sets up button click listeners for various functionalities.
     * Each button opens a URL in a web browser.
     */
    private void Buttons() {
        findViewById(R.id.btnHandRecognition).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnGlossingSASL).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnTextToAvatar).setOnClickListener(view -> openUrl("https://www.google.com"));
        findViewById(R.id.btnFeedback).setOnClickListener(view -> openUrl("https://www.google.com"));
    }

    /**
     * Opens a given URL in a web browser.
     * @param url The URL to open
     */
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); // Create an intent to open the URL
        startActivity(intent); // Start the browser activity
    }
}
