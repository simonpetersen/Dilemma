package model;

import java.util.ArrayList;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaListe {

    private ArrayList<Dilemma> dilemmaList;
    private int valgtDilemmaPosition;

    public DilemmaListe() {
        dilemmaList = new ArrayList<Dilemma>();
    }

    public ArrayList<String> getTitles() {
        ArrayList<String> Titles = new ArrayList<String>();
        for( Dilemma i: dilemmaList){
            Titles.add(i.getTitel());
        }
        return Titles;
    }

    public ArrayList<Dilemma> getDilemmaListe() {return dilemmaList;}

    public void selectDilemma(int position) { valgtDilemmaPosition = position; }

    public Dilemma getValgtDilemma() { return dilemmaList.get(valgtDilemmaPosition); }

    public void addDilemma(Dilemma dilemma) {
        dilemmaList.add(dilemma);
    }

    public Dilemma getDilemma(int dilemmaID) {
        for (Dilemma d : dilemmaList)
            if (d.getDilemmaID() == dilemmaID) return d;
        return null;
    }

    public String toString() {
        return dilemmaList.toString();
    }
}
