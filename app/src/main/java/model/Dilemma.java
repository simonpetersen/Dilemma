package model;

import java.util.ArrayList;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int seriøsitet, svartid;
    private String titel, beskrivelse, kategori;
    private ArrayList<String> svarmuligheder;

    public Dilemma(String titel, String beskrivelse, String kategori, int seriøsitet, int svartid, ArrayList<String> svarmuligheder){
        this.titel = titel;
        this.beskrivelse = beskrivelse;
        this.kategori = kategori;
        this.seriøsitet = seriøsitet;
        this.svartid = svartid;
        this.svarmuligheder = svarmuligheder;
    }

    public Dilemma() {}

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

}
