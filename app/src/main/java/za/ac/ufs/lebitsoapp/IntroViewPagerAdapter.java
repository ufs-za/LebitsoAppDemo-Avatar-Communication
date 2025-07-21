package za.ac.ufs.lebitsoapp;

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
 * Adapter class for the ViewPager used in the IntroActivity.
 * It binds a list of ScreenItem objects to their corresponding layout.
 */
public class IntroViewPagerAdapter extends PagerAdapter {

    private Context mContext;                  //Application context
    private List<ScreenItem> mListScreen;      //List of screen data (title, desc, image)

    //Constructor
    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    /**
     * Creates and inflates the layout for a given position in the ViewPager.
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Inflate the layout for the onboarding screen
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        //Get references to UI components in the layout
        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        //Set data from the ScreenItem object
        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        //Add the populated view to the container
        container.addView(layoutScreen);

        return layoutScreen;
    }

    /**
     * Returns the number of items (pages) in the ViewPager.
     */
    @Override
    public int getCount() {
        return mListScreen.size();
    }

    /**
     * Determines whether a page View is associated with a specific key object.
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * Removes a page from the container.
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
