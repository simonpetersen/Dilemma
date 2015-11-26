package model;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int serious;
    private String title, description, category;

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setCategory(String category) { this.category = category; }
}
