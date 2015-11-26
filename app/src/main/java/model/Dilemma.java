package model;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int serious;
    private String title, description, category;

    public Dilemma(String title, String description, String category, int serious) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.serious = serious;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
