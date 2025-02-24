//IntroActivity.java - This is the entry point of the app

//Dependencies
package com.example.lebitsoapp;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//IntroActivity class
public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button btnNext;
    private Button btnBack;
    private int position = -1;
    private Button btnGetStarted;
    private Animation btnAnim;
    private TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Remove the check to always show the intro screen
        setContentView(R.layout.activity_intro);

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back);
        btnGetStarted = findViewById(R.id.btn_getstarted);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // Fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome Labitso User", "Welcome to version 1.0 of the Labitso App. For the best experience please use it in portrait. \nThis app was developed with project funding from the Department of Sport, Arts and Culture.", R.drawable.dsac2));
        mList.add(new ScreenItem("About the App", "Every button in this app is a functional GIF because it was designed for the Deaf community.", R.drawable.welcome));
        mList.add(new ScreenItem("Enjoy the Experience", "In this app you will find videos and links to other deliverables in the project.", R.drawable.thank_you));

        // Setup ViewPager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // Setup TabLayout with ViewPager
        tabIndicator.setupWithViewPager(screenPager);

        // Next button click listener
        btnNext.setOnClickListener(v -> {
            position = screenPager.getCurrentItem(); // Get the current page

            if (position < 2) { // Ensure position doesn't exceed the last page (index 2)
                position++;
                screenPager.setCurrentItem(position); // Move to the next page
            }

            // Handle reaching the last page
            if (position == 2) {
                loadLastScreen(); // Customize UI for the last page
            }
        });

        //Handling navigation of ScreenPager
        btnBack.setOnClickListener(v -> {
            position = screenPager.getCurrentItem(); // Get the current page

            if (position > 0) { // Ensure position doesn't go below the first page (index 0)
                position--;
                screenPager.setCurrentItem(position); // Move to the previous page
            }

            // Handle reaching the first page
            if (position == 0) {
                loadFirstScreen(); // Customize UI for the first page
            }
        });

        // TabLayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                screenPager.setCurrentItem(position); // Sync tab selection with the pager
                if (position == 0) {
                    loadFirstScreen(); // First screen logic
                } else if (position == 1) {
                    loadSecondScreen(); // Last screen logic
                } else if (position == 2) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // Get Started button click listener
        btnGetStarted.setOnClickListener(v -> {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        });

        // Skip button click listener
        tvSkip.setOnClickListener(v -> {
            screenPager.setCurrentItem(mList.size());
            loadLastScreen();
        });
    }

    // Method for determining btn and icon visibility
    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        // Add animation to the Get Started button
        btnGetStarted.setAnimation(btnAnim);

        // Change the button color to the custom color using tint
        btnGetStarted.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.signup_button_default_color));


    }

    // Method for determining btn and icon visibility
    private void loadSecondScreen() {
        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
        btnGetStarted.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }

    // Method for determining btn and icon visibility
    private void loadFirstScreen() {
        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }
}