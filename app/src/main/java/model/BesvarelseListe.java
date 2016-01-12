package model;

import java.util.ArrayList;

/**
 * Created by Simon on 12/01/16.
 */
public class BesvarelseListe {
    private int dilemmaID;
    private ArrayList<Besvarelse> besvarelser;

    public BesvarelseListe() { }

    public void addBesvarelse(Besvarelse besvarelse) {
        besvarelser.add(besvarelse);
    }

    public ArrayList<Besvarelse> getBesvarelser() {
        return besvarelser;
    }

    public void setBesvarelser(ArrayList<Besvarelse> besvarelser) {
        this.besvarelser = besvarelser;
    }

    public int getDilemmaID() {
        return dilemmaID;
    }

    public void setDilemmaID(int dilemmaID) {
        this.dilemmaID = dilemmaID;
    }
}
