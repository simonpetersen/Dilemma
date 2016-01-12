package model;

/**
 * Created by Sandie on 11-01-2016.
 */
public class Besvarelse {
    private int besvarelse;
    private String kommentar, brugerID;

    public Besvarelse() { }

    public Besvarelse(int besvarelse, String kommentar, String brugerID) {
        this.besvarelse = besvarelse;
        this.kommentar = kommentar;
        this.brugerID = brugerID;
    }

    public int getBesvarelse() {
        return besvarelse;
    }

    public void setBesvarelse(int besvarelse) {
        this.besvarelse = besvarelse;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(String brugerID) {
        this.brugerID = brugerID;
    }

    public String toString() {
        return besvarelse+", "+kommentar+", "+brugerID;
    }
}
