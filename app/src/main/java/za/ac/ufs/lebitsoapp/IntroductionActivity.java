//IntroductionActivity.java

//Dependencies

package za.ac.ufs.lebitsoapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.exoplayer2.ui.PlayerView;

//InductionActivity class
public class IntroductionActivity extends AppCompatActivity {

    private PlayerView playerView;
    private ImageView bt_fullscreen;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set fullscreen mode
        Utility.TrueFullscreen(this);

        //Set layout for the activity
        setContentView(R.layout.activity_introduction);

        //Initialize UI components
        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        playerView = findViewById(R.id.player);
        bt_fullscreen = findViewById(R.id.bt_fullscreen);

        //Call utility methods to setup navigation for toolbar and navbar
        Utility.enableTopBarBackButton(this,toolbar,MainActivity.class);
        Utility.setupBottomNavigation(this,bottomNavigationView,MainActivity.class);

        //Call videoplayeractivity to allow for initialization of exoplayer and handling
        //screen orientation of player
        VideoPlayerActivity.initializePlayer(this, playerView, "android.resource://"
                + getPackageName() + "/" + R.raw.vid_introduction);
        VideoPlayerActivity.handleScreenOrientation(this, bt_fullscreen);
    }

    //Lifecycle helpers
    @Override
    protected void onPause() {
        super.onPause();
        VideoPlayerActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoPlayerActivity.onDestroy();
    }
    //Lifecycle helpers
}

