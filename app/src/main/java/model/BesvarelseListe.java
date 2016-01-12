package model;

import java.util.ArrayList;

/**
 * Created by Simon on 12/01/16.
 */
public class BesvarelseListe {
    private int dilemmaID;
    private int[] svar;
    //private ArrayList<Besvarelse> besvarelser;
    private ArrayList<String> kommentarer, besvarerID;

    public BesvarelseListe() {
        //besvarelser = new ArrayList<>();
        kommentarer = new ArrayList<>();
        besvarerID = new ArrayList<>();
    }

    public BesvarelseListe(Dilemma d) {
        this.dilemmaID = d.getDilemmaID();
        svar = new int[d.getSvarmuligheder().size()];
        for (int i : svar) i = 0;
        //besvarelser = new ArrayList<>();
        kommentarer = new ArrayList<>();
        besvarerID = new ArrayList<>();
    }

    /*
    public void addBesvarelse(Besvarelse besvarelse) {
        besvarelser.add(besvarelse);
    }

    public ArrayList<Besvarelse> getBesvarelser() {
        return besvarelser;
    }

    public void setBesvarelser(ArrayList<Besvarelse> besvarelser) {
        this.besvarelser = besvarelser;
    }
    */

    public int[] getSvar() { return svar; }

    public void setSvar(int[] svar) { this.svar = svar; }

    public int getDilemmaID() {
        return dilemmaID;
    }

    public void setDilemmaID(int dilemmaID) {
        this.dilemmaID = dilemmaID;
    }

    public ArrayList<String> getKommentarer() {
        return kommentarer;
    }

    public void setKommentarer(ArrayList<String> kommentarer) {
        this.kommentarer = kommentarer;
    }

    public ArrayList<String> getBesvarerID() {
        return besvarerID;
    }

    public void setBesvarerID(ArrayList<String> besvarerID) {
        this.besvarerID = besvarerID;
    }

    public void addDilemma(int svarValg, String kommentar, String brugerID) {
        svar[svarValg-1]++;
        if (!besvarerID.contains(brugerID)) besvarerID.add(brugerID);
        if (!kommentar.equals("")) kommentarer.add(kommentar);
    }
}
