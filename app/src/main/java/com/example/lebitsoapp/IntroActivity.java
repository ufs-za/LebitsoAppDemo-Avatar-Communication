package com.example.lebitsoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    // UI components
    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button btnNext, btnBack, btnGetStarted;
    private TextView tvSkip;
    private Animation btnAnim;
    private int position = -1;
    private List<ScreenItem> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trueFullscreen(); // Enable fullscreen mode

        setContentView(R.layout.activity_intro);

        // Initialize UI components
        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back);
        btnGetStarted = findViewById(R.id.btn_getstarted);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);
        mlist = getScreenItems(); // Initialize screen items list

        setupViewPager(); // Set up ViewPager with the intro screens
        setupListeners(); // Set up click listeners for navigation
    }

    // Sets up the ViewPager with screen items and links it to the TabLayout
    private void setupViewPager() {
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mlist);
        screenPager.setAdapter(introViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager);
    }

    // Sets up event listeners for buttons and tab navigation
    private void setupListeners() {
        btnNext.setOnClickListener(v -> navigateNext());
        btnBack.setOnClickListener(v -> navigateBack());
        tvSkip.setOnClickListener(v -> skipIntro());
        btnGetStarted.setOnClickListener(v -> startMainActivity());

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                handleTabSelection(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    // Creates a list of screen items for the introduction
    private List<ScreenItem> getScreenItems() {
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome Labitso User", "Welcome to version 1.0 of the Labitso App. For the best experience please use it in portrait. \nThis app was developed with project funding from the Department of Sport, Arts and Culture.", R.drawable.dsac2));
        mList.add(new ScreenItem("About the App", "Every button in this app is a functional GIF because it was designed for the Deaf community.", R.drawable.welcome));
        mList.add(new ScreenItem("Enjoy the Experience", "In this app you will find videos and links to other deliverables in the project.", R.drawable.thank_you));
        return mList;
    }

    // Enables fullscreen mode by removing the title and setting flags
    private void trueFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // Handles "Next" button click event to navigate forward
    private void navigateNext() {
        position = screenPager.getCurrentItem();
        if (position < mlist.size() - 1) {
            screenPager.setCurrentItem(++position);
        }
        if (position == mlist.size() - 1) {
            loadLastScreen();
        }
    }

    // Handles "Back" button click event to navigate backward
    private void navigateBack() {
        position = screenPager.getCurrentItem();
        if (position > 0) {
            screenPager.setCurrentItem(--position);
        }
        if (position == 0) {
            loadFirstScreen();
        }
    }

    // Skips the introduction and moves to the last screen
    private void skipIntro() {
        screenPager.setCurrentItem(mlist.size());
        loadLastScreen();
    }

    // Starts the main activity after the introduction
    private void startMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    // Handles tab selection to switch between screens
    private void handleTabSelection(int position) {
        screenPager.setCurrentItem(position);
        if (position == 0) {
            loadFirstScreen();
        } else if (position == 1) {
            loadSecondScreen();
        } else if (position == 2) {
            loadLastScreen();
        }
    }

    // Displays the last screen and adjusts UI accordingly
    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);
        btnGetStarted.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.signup_button_default_color));
    }

    // Displays the second screen with UI adjustments
    private void loadSecondScreen() {
        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
        btnGetStarted.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }

    // Displays the first screen with UI adjustments
    private void loadFirstScreen() {
        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }
}
