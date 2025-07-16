package com.example.lebitsoapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

/**
 * Utility class to handle ExoPlayer video playback and screen orientation toggling.
 */
public class VideoPlayerActivity {

    // Tracks current screen orientation
    private static boolean isFullScreen = false;

    // Singleton ExoPlayer instance
    private static SimpleExoPlayer simpleExoPlayer;

    /**
     * Initializes the ExoPlayer with the provided video URL and starts playback.
     *
     * @param context    The context used to create the player
     * @param playerView The PlayerView in which the video will be displayed
     * @param videoUrl   The URL of the video to be played
     * @return A configured and playing SimpleExoPlayer instance
     */
    public static SimpleExoPlayer initializePlayer(Context context, PlayerView playerView, String videoUrl) {
        //Create a new ExoPlayer instance
        simpleExoPlayer = new SimpleExoPlayer.Builder(context)
                .setSeekBackIncrementMs(5000)     // Customize rewind step
                .setSeekForwardIncrementMs(5000)  // Customize forward step
                .build();

        //Attach player to UI
        playerView.setPlayer(simpleExoPlayer);

        //Prevent screen from sleeping during playback
        playerView.setKeepScreenOn(true);

        //Load the media from the URI
        Uri videoUri = Uri.parse(videoUrl);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);

        simpleExoPlayer.setMediaItem(mediaItem);

        //Prepare player
        simpleExoPlayer.prepare();

        //Start playback
        simpleExoPlayer.setPlayWhenReady(true);

        return simpleExoPlayer;
    }

    /**
     * Restarts and plays the video from the beginning.
     */
    public static void onPlay() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.seekTo(0); //Restart video
            simpleExoPlayer.prepare(); //Re-buffer
            simpleExoPlayer.setPlayWhenReady(true); //Play
        }
    }

    /**
     * Pauses the video (typically called in activity onPause).
     */
    public static void onPause() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.pause();
        }
    }

    /**
     * Stops the video playback (typically called in activity onStop).
     */
    public static void onStop() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
        }
    }

    /**
     * Releases the ExoPlayer instance to free up resources (typically called in activity onDestroy).
     */
    public static void onDestroy() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    /**
     * Handles screen orientation toggle between portrait and fullscreen landscape.
     * Updates the fullscreen icon based on the current state.
     *
     * @param activity       Current activity context
     * @param bt_fullscreen  Fullscreen toggle button (ImageView)
     */
    public static void handleScreenOrientation(Activity activity, ImageView bt_fullscreen) {
        if (bt_fullscreen != null) {
            bt_fullscreen.setOnClickListener(v -> {
                if (!isFullScreen) {
                    //Switch to landscape mode and update icon
                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_fullscreen_exit));
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                } else {
                    //Revert to portrait mode and update icon
                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_fullscreen_open));
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                isFullScreen = !isFullScreen;
            });
        }
    }
}
