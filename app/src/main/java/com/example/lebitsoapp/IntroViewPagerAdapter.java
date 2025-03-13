// Package declaration
package com.example.lebitsoapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * IntroViewPagerAdapter is a custom adapter class for ViewPager.
 * It is used to display a series of introduction screens (slides)
 * in an onboarding activity.
 */
public class IntroViewPagerAdapter extends PagerAdapter {

    // Context of the application (used to inflate layouts)
    private Context mContext;

    // List of ScreenItem objects, each representing a slide with an image, title, and description
    private List<ScreenItem> mListScreen;

    /**
     * Constructor to initialize the adapter with context and data.
     * @param mContext The context in which the adapter is used.
     * @param mListScreen The list of ScreenItem objects representing slides.
     */
    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    /**
     * Creates and initializes the view for a specific position in the ViewPager.
     * @param container The parent ViewGroup that holds the pages.
     * @param position The position of the current item.
     * @return The created view for the given position.
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Inflate the layout for the slide
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        // Get references to UI elements in the slide layout
        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        // Set the content for the current slide
        title.setText(mListScreen.get(position).getTitle()); // Set title text
        description.setText(mListScreen.get(position).getDescription()); // Set description text
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg()); // Set image resource

        // Add the created view to the ViewPager container
        container.addView(layoutScreen);

        // Return the created view
        return layoutScreen;
    }

    /**
     * Returns the total number of slides available.
     * @return The size of the mListScreen list.
     */
    @Override
    public int getCount() {
        return mListScreen.size();
    }

    /**
     * Determines whether a given view corresponds to a specific object (slide).
     * @param view The current view.
     * @param o The object to compare.
     * @return True if the view and object are the same, false otherwise.
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * Removes a view from the container when it is no longer needed.
     * This helps in efficient memory management by recycling views.
     * @param container The parent ViewGroup that holds the pages.
     * @param position The position of the item to be removed.
     * @param object The object representing the view.
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove the view from the container
        container.removeView((View) object);
    }
}
