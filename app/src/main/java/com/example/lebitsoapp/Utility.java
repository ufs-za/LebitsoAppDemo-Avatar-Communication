package com.example.lebitsoapp;

import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Utility class containing helper methods for UI setup across activities.
 */
public class Utility {

    /**
     * Enables a back button in the top app bar (toolbar).
     * Navigates back to the specified target activity when clicked.
     *
     * @param activity       Current AppCompatActivity
     * @param toolbar        Toolbar where the back button will be shown
     * @param targetActivity Activity to return to when back button is pressed
     */
    public static void enableTopBarBackButton(AppCompatActivity activity, Toolbar toolbar, Class<?> targetActivity) {
        //Enable back button in the action bar
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Set click listener to go back to the target activity
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(activity, targetActivity);

            //Ensure the target activity is brought to the top of the stack
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            activity.startActivity(intent);

            //Close current activity
            activity.finish();
        });
    }

    /**
     * Sets up bottom navigation so that clicking the Home icon returns the user to the home screen.
     *
     * @param activity     Current activity
     * @param bottomNav    BottomNavigationView object from the layout
     * @param homeActivity Activity class to navigate to when "Home" is selected
     */
    public static void setupBottomNavigation(Activity activity, BottomNavigationView bottomNav, Class<?> homeActivity) {
        if (bottomNav != null) {
            bottomNav.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.nav_home) {
                    Intent intent = new Intent(activity, homeActivity);
                    //Clear back stack and start home activity fresh
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                    //End current activity
                    activity.finish();
                    return true;
                }
                return false;
            });
        }
    }

    /**
     * Enables full-screen mode for the activity, hiding the status bar and title.
     *
     * @param activity Activity to apply full-screen settings to
     */
    public static void TrueFullscreen(Activity activity) {
        //Request no title for the activity
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set flags to enable full-screen mode (no status bar)
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }
}
