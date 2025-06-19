package com.example.lebitsoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

/**
 * IntroActivity shows an onboarding screen when the app launches for the first time.
 * Users can navigate through informative slides and then proceed to the main app.
 */
public class IntroActivity extends AppCompatActivity {

    // UI components
    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button btnNext, btnBack, btnGetStarted;
    private TextView tvSkip;
    private Animation btnAnim;

    // Tracks current screen position
    private int position = -1;

    // List holding data for each screen in the onboarding
    private List<ScreenItem> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set fullscreen mode
        Utility.TrueFullscreen(this);

        //Set layout for the activity
        setContentView(R.layout.activity_intro);

        //Initialize UI components
        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back);
        btnGetStarted = findViewById(R.id.btn_getstarted);
        tabIndicator = findViewById(R.id.tab_indicator);
        tvSkip = findViewById(R.id.tv_skip);

        //Load button animation
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        //Populate screen items (slides)
        mlist = getScreenItems();

        //Setup ViewPager and adapter
        setupViewPager();

        //Set up button and tab listeners
        setupListeners();
    }

    /**
     * Initializes and attaches the ViewPager with its adapter.
     */
    private void setupViewPager() {
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mlist);
        screenPager.setAdapter(introViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager); // Connect tab indicator to pager
    }

    /**
     * Assigns listeners to navigation buttons and tab indicators.
     */
    private void setupListeners() {
        btnNext.setOnClickListener(v -> navigateNext());     // Go to next screen
        btnBack.setOnClickListener(v -> navigateBack());     // Go to previous screen
        tvSkip.setOnClickListener(v -> skipIntro());         // Skip to last screen
        btnGetStarted.setOnClickListener(v -> startMainActivity()); // Enter main app

        // Handle tab selection events
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

    /**
     * Creates a list of screens to show in the onboarding.
     */
    private List<ScreenItem> getScreenItems() {
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome Lebitso User",
                "Welcome to version 1.0 of the Lebitso App. For the best experience please use it in portrait. " +
                        "\nThis app was developed with project funding from the Department of Sport, Arts and Culture.",
                R.drawable.dsac2));
        mList.add(new ScreenItem("About the App",
                "Every button in this app is a functional GIF because it was designed for the Deaf community.",
                R.drawable.welcome));
        mList.add(new ScreenItem("Enjoy the Experience",
                "In this app you will find videos and links to other deliverables in the project.",
                R.drawable.thank_you));
        return mList;
    }

    /**
     * Navigates to the next screen in the ViewPager.
     */
    private void navigateNext() {
        position = screenPager.getCurrentItem();
        if (position < mlist.size() - 1) {
            screenPager.setCurrentItem(++position);
        }
        if (position == mlist.size() - 1) {
            loadLastScreen();
        }
    }

    /**
     * Navigates to the previous screen in the ViewPager.
     */
    private void navigateBack() {
        position = screenPager.getCurrentItem();
        if (position > 0) {
            screenPager.setCurrentItem(--position);
        }
        if (position == 0) {
            loadFirstScreen();
        }
    }

    /**
     * Skips onboarding to the last screen directly.
     */
    private void skipIntro() {
        screenPager.setCurrentItem(mlist.size());
        loadLastScreen();
    }

    /**
     * Starts the main activity and finishes the intro.
     */
    private void startMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    /**
     * Handles UI state when a new tab is selected.
     */
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

    /**
     * Displays the last screen: hide navigation, show "Get Started" button.
     */
    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim); // Apply animation
        btnGetStarted.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.signup_button_default_color));
    }

    /**
     * Displays the second screen with all navigation buttons visible.
     */
    private void loadSecondScreen() {
        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
        btnGetStarted.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }

    /**
     * Displays the first screen: hide back button.
     */
    private void loadFirstScreen() {
        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }
}
