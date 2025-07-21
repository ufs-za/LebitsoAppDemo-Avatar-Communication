package za.ac.ufs.lebitsoapp;

/**
 * Model class representing a single item in the introduction/onboarding screen.
 * Each item contains a title, description, and an image resource ID.
 */
public class ScreenItem {

    //Fields to store title, description, and image resource ID
    private final String title;
    private final String description;
    private final int screenImg;

    /**
     * Constructor to initialize the screen item with data.
     *
     * @param title       Title text for the screen
     * @param description Description text for the screen
     * @param screenImg   Resource ID of the image to display
     */
    public ScreenItem(String title, String description, int screenImg) {
        this.title = title;
        this.description = description;
        this.screenImg = screenImg;
    }

    //Getter for the title
    public String getTitle() {
        return title;
    }

    //Getter for the description
    public String getDescription() {
        return description;
    }

    //Getter for the image resource ID
    public int getScreenImg() {
        return screenImg;
    }
}
