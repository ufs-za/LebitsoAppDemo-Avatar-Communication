// Package declaration: Defines the package where this class belongs.
package com.example.lebitsoapp;

/**
 * The ScreenItem class represents a screen item with a title, description, and an image.
 * It is primarily used to store data for displaying in an onboarding or tutorial screen.
 */
public class ScreenItem {

    // Private instance variables to hold screen item details
    private String Title;       // The title of the screen item
    private String Description; // A brief description of the screen item
    private int ScreenImg;      // The resource ID of the screen image (e.g., R.drawable.image)

    /**
     * Constructor: Initializes a new ScreenItem object with the given parameters.
     *
     * @param title       The title of the screen item.
     * @param description A brief description of the screen item.
     * @param screenImg   The resource ID of the screen image.
     */
    public ScreenItem(String title, String description, int screenImg) {
        this.Title = title;
        this.Description = description;
        this.ScreenImg = screenImg;
    }

    /**
     * Sets the title of the screen item.
     *
     * @param title The new title to be set.
     */
    public void setTitle(String title) {
        this.Title = title;
    }

    /**
     * Sets the description of the screen item.
     *
     * @param description The new description to be set.
     */
    public void setDescription(String description) {
        this.Description = description;
    }

    /**
     * Sets the image resource ID for the screen item.
     *
     * @param screenImg The new image resource ID to be set.
     */
    public void setScreenImg(int screenImg) {
        this.ScreenImg = screenImg;
    }

    /**
     * Retrieves the title of the screen item.
     *
     * @return The current title of the screen item.
     */
    public String getTitle() {
        return this.Title;
    }

    /**
     * Retrieves the description of the screen item.
     *
     * @return The current description of the screen item.
     */
    public String getDescription() {
        return this.Description;
    }

    /**
     * Retrieves the image resource ID of the screen item.
     *
     * @return The current image resource ID of the screen item.
     */
    public int getScreenImg() {
        return this.ScreenImg;
    }

}
