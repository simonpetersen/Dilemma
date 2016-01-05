package model;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int serious, time;
    private String title, description, category;
    private String answer1, answer2, answer3, answer4, answer5;

    public Dilemma(String title, String description, String category, int serious, int time, String answer1, String answer2, String answer3, String answer4, String answer5){
        this.title = title;
        this.description = description;
        this.category = category;
        this.serious = serious;
        this.time = time;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
    }
    public Dilemma(){}

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public int getSerious() {return serious;}

    public String getCategory() {return category;}

    public int getTime() {return time; }

    public String getAnswer1() {return answer1;}

    public String getAnswer2() {return answer2;}

    public String getAnswer3() {return answer3;}

    public String getAnswer4() {return answer4;}

    public String getAnswer5() {return answer5;}

    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setCategory(String category) { this.category = category; }

    public void setSerious(int serious) {this.serious = serious;}

    public void setTime(int time) {this.time = time;}

    public void setAnswer1(String answer1) {this.answer1 = answer1;}

    public void setAnswer2(String answer2) {this.answer2 = answer2;}

    public void setAnswer3(String answer3) {this.answer3 = answer3;}

    public void setAnswer4(String answer4) {this.answer4 = answer4;}

    public void setAnswer5(String answer5) {this.answer5 = answer5;}
}
