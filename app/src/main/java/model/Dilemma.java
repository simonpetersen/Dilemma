package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int seriøsitet;
    private long svartidspunkt;
    private String titel, beskrivelse, kategori;
    private ArrayList<String> svarmuligheder;
    private ArrayList<String> svar;
    private boolean comment;

    public Dilemma(String titel, String beskrivelse, String kategori, int seriøsitet, long svartidspunkt, boolean comment, ArrayList<String> svarmuligheder){
        this.titel = titel;
        this.beskrivelse = beskrivelse;
        this.kategori = kategori;
        this.seriøsitet = seriøsitet;
        this.svartidspunkt = svartidspunkt;
        this.svarmuligheder = svarmuligheder;
        this.comment = comment;
    }

    public Dilemma() {}

    public String getTitel() { return titel; }

    public String getBeskrivelse() { return beskrivelse; }

    public int getSeriøsitet() {return seriøsitet;}

    public String getKategori() {return kategori;}

    public long getSvartidspunkt() {return svartidspunkt; }

    public boolean getComment() {return comment;}

    public ArrayList<String> getSvar() {return svar;}

    public void setSvarmuligheder(ArrayList<String> options) { svarmuligheder = options; }

    public ArrayList<String> getSvarmuligheder() { return svarmuligheder; }

    public int getNumberOfAnswerOptions() { return svarmuligheder.size(); }

    public void setTitel(String titel) { this.titel = titel; }

    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    public void setKategori(String kategori) { this.kategori = kategori; }

    public void setSeriøsitet(int seriøsitet) {this.seriøsitet = seriøsitet;}

    public void setSvartidspunkt(long svartidspunkt) {this.svartidspunkt = svartidspunkt;}

    public void setComment(boolean comment) {this.comment = comment;}

    public void setSvar(ArrayList<String> svar) {this.svar = svar;}

}
