//MainActivity

//Dependencies
package za.ac.ufs.lebitsoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the activity full screen
        Utility.TrueFullscreen(this);

        //Set view for activity
        setContentView(R.layout.activity_main);

        //Button Functions Section
        buttons();

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
            Intent intent = new Intent(MainActivity.this, CollaboratorsActivity.class);
            startActivity(intent);
        });

        //Feedback Button
        findViewById(R.id.btnFeedback).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://forms.office.com/r/WgFPnHfv6y")); // Replace with your desired URL
            startActivity(intent);
        });
    }
}
