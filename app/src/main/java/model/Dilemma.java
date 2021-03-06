package model;

import java.util.ArrayList;

/**
 * Created by Simon on 26/11/15.
 */
public class Dilemma {
    private int seriøsitet, dilemmaID;
    private long svartidspunkt;
    private String titel, beskrivelse, kategori, opretterID, svarkommentar;
    private ArrayList<String> svarmuligheder, billedeUrl;
    private boolean kommentarTilladt;

    public Dilemma(int dilemmaID, String titel, String beskrivelse, String kategori, int seriøsitet, long svartidspunkt, boolean kommentarTilladt, ArrayList<String> svarmuligheder){
        this.dilemmaID = dilemmaID;
        this.titel = titel;
        this.beskrivelse = beskrivelse;
        this.kategori = kategori;
        this.seriøsitet = seriøsitet;
        this.svartidspunkt = svartidspunkt;
        this.svarmuligheder = svarmuligheder;
        this.kommentarTilladt = kommentarTilladt;
        billedeUrl = new ArrayList<>();
    }

    public Dilemma() {

        billedeUrl = new ArrayList<>();
        svarmuligheder = new ArrayList<>();
    }

    public String getTitel() { return titel; }

    public String getBeskrivelse() { return beskrivelse; }

    public int getSeriøsitet() {return seriøsitet;}

    public String getKategori() {return kategori;}

    public long getSvartidspunkt() {return svartidspunkt; }

    public boolean getKommentarTilladt() {return kommentarTilladt;}

    public String getSvarkommentar() {return svarkommentar;}

    public void setSvarmuligheder(ArrayList<String> options) { svarmuligheder = options; }

    public ArrayList<String> getSvarmuligheder() { return svarmuligheder; }

    public void setTitel(String titel) { this.titel = titel; }

    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    public void setKategori(String kategori) { this.kategori = kategori; }

    public void setSeriøsitet(int seriøsitet) {this.seriøsitet = seriøsitet;}

    public void setSvartidspunkt(long svartidspunkt) {this.svartidspunkt = svartidspunkt;}

    public void setKommentarTilladt(boolean kommentarTilladt) {this.kommentarTilladt = kommentarTilladt;}

    public void setSvarkommentar(String svarkommentar) {this.svarkommentar = svarkommentar;}

    public int getDilemmaID() {
        return dilemmaID;
    }

    public void setDilemmaID(int dilemmaID) {
        this.dilemmaID = dilemmaID;
    }

    public ArrayList<String> getBilledeUrl() {
        return billedeUrl;
    }

    public void setBilledeUrl(ArrayList<String> billedeUrl) {
        this.billedeUrl = billedeUrl;
    }

    public void addBilledeUrl(String url) {
        billedeUrl.add(url);
    }

    public String getOpretterID() {
        return opretterID;
    }

    public void setOpretterID(String opretterID) {
        this.opretterID = opretterID;
    }

    public boolean erAktivt(long tidNu) {
        return svartidspunkt > tidNu;
    }

    public String toString() { return titel; }
}
