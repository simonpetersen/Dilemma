package model;

import java.util.ArrayList;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaList {

    private ArrayList<Dilemma> dilemmaList;
    private int selectedDilemmaPosition;


    public DilemmaList() {
        dilemmaList = new ArrayList<Dilemma>();
        dilemmaList.add(new Dilemma("Hjælp til bukser", "Hjælp mig med hvilke bukser jeg skal tage på.", "Hobby", 3, 10));
        dilemmaList.add(new Dilemma("Hjælp til kjoler", "Hvilken kjole skal jeg købe til bryllupet?", "Fest", 2, 10));
        dilemmaList.add(new Dilemma("Skal hunden dø?", "Skal min kræftsyge hund aflives?", "Personlig", 5, 60));
        dilemmaList.add(new Dilemma("Hvornår skal jeg sige stop?", "Min kone bliver ved med at skifte mine ting i lejligheden ud med hendes.. Hvad skal jeg gøre?", "Personligt", 3, 20));
    }

    public ArrayList<String> getTitles() {
        ArrayList<String> Titles = new ArrayList<String>();
        for( Dilemma i: dilemmaList){
            Titles.add(i.getTitle());
        }
        return Titles;
    }

    public ArrayList<Dilemma> getDilemmaList() {return dilemmaList;}

    public Dilemma getDilemma(int position) { return dilemmaList.get(position); }

    public void selectDilemma(int position) { selectedDilemmaPosition = position; }

    public Dilemma getSelectedDilemma() { return dilemmaList.get(selectedDilemmaPosition); }

    public void addDilemma(Dilemma dilemma) {
        dilemmaList.add(dilemma);
    }

}
