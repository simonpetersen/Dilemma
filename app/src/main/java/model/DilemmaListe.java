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
        dilemmaList.add(new Dilemma("Hjælp til bukser", "Hjælp mig med hvilke bukser jeg skal tage på.", "Hobby", 3, 10));
        dilemmaList.add(new Dilemma("Hjælp til kjoler", "Hvilken kjole skal jeg købe til bryllupet?", "Fest", 2, 10));
        dilemmaList.add(new Dilemma("Skal hunden dø?", "Skal min kræftsyge hund aflives?", "Personlig", 5, 60));
        dilemmaList.add(new Dilemma("Hvornår skal jeg sige stop?", "Min kone bliver ved med at skifte mine ting i lejligheden ud med hendes.. Hvad skal jeg gøre?", "Personligt", 3, 20));
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

}
