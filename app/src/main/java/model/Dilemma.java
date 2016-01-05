package model;

import java.util.ArrayList;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int seriøsitet, svartid;
    private String titel, beskrivelse, kategori;
    private String answer1, answer2, answer3, answer4, answer5;
    private ArrayList<String> svarmuligheder;

    public Dilemma(String titel, String beskrivelse, String kategori, int seriøsitet, int svartid){
        this.titel = titel;
        this.beskrivelse = beskrivelse;
        this.kategori = kategori;
        this.seriøsitet = seriøsitet;
        this.svartid = svartid;
    }
    public Dilemma(){}

    public String getTitel() { return titel; }

    public String getBeskrivelse() { return beskrivelse; }

    public int getSeriøsitet() {return seriøsitet;}

    public String getKategori() {return kategori;}

    public int getSvartid() {return svartid; }

    public void setSvarmuligheder(ArrayList<String> options) { svarmuligheder = options; }

    public int getNumberOfAnswerOptions() { return svarmuligheder.size(); }

    public void setTitel(String titel) { this.titel = titel; }

    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    public void setKategori(String kategori) { this.kategori = kategori; }

    public void setSeriøsitet(int seriøsitet) {this.seriøsitet = seriøsitet;}

    public void setSvartid(int svartid) {this.svartid = svartid;}

    public void setAnswer1(String answer1) {this.answer1 = answer1;}

    public void setAnswer2(String answer2) {this.answer2 = answer2;}

    public void setAnswer3(String answer3) {this.answer3 = answer3;}

    public void setAnswer4(String answer4) {this.answer4 = answer4;}

    public void setAnswer5(String answer5) {this.answer5 = answer5;}

    public String getAnswer1() {return answer1;}

    public String getAnswer2() {return answer2;}

    public String getAnswer3() {return answer3;}

    public String getAnswer4() {return answer4;}

    public String getAnswer5() {return answer5;}
}
