package model;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int serious, time;
    private String title, description, category;

    public Dilemma(String title, String description, String category, int serious, int time){
        this.title = title;
        this.description = description;
        this.category = category;
        this.serious = serious;
        this.time = time;
    }
    public Dilemma(){}

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public int getSerious() {return serious;}

    public String getCategory() {return category;}

    public int getTime() {return time; }

    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setCategory(String category) { this.category = category; }
}
