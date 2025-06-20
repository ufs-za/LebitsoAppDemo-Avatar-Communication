package com.example.lebitsoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DRLinksActivity extends AppCompatActivity {

    //Declare UI components
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the activity to fullscreen using a utility method
        Utility.TrueFullscreen(this);

        //Link the activity to its XML layout file
        setContentView(R.layout.activity_drlinks_actvity);

        //Initialize BottomNavigationView and Toolbar from layout
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar5);

        //Enable a back button in the top toolbar (for navigation)
        Utility.enableTopBarBackButton(this, toolbar, DRLinksActivity.class);

        //Setup the bottom navigation bar with its behavior
        Utility.setupBottomNavigation(this, bottomNavigationView, MainActivity.class);

        //Set up button click listeners
        Buttons();
    }

    /**
     *Assigns click listeners to buttons and opens respective URLs when clicked.
     *Currently, all buttons open Google, but these can be changed to any desired URL.
     */
    private void Buttons() {
        findViewById(R.id.btn_Link1).setOnClickListener(view ->
                openUrl("https://pokello.ufs.ac.za/s/sasl"));

        findViewById(R.id.btn_Link2).setOnClickListener(view ->
                openUrl("https://figshare.com/articles/dataset/South_African_Place_Names/23741892"));

        findViewById(R.id.btn_Link3).setOnClickListener(view ->
                openUrl("https://github.com/ufs-za/human_language_technology_for_sasl"));

        findViewById(R.id.btn_Link4).setOnClickListener(view ->
                openUrl("https://figshare.com/articles/report/Advancing_South_African_Sign_Language_for_4IR_Technological_Development/28847498"));
    }

    /**
     *Opens a URL in the device's default browser using an implicit intent.
     *
     *@param url The web address to open
     */
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
