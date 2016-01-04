package model;

import java.util.ArrayList;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaList {

    ArrayList<String> overskrifter;
    ArrayList<String> beskrivelser;

    ArrayList<Dilemma> dilemmaListe;


    public DilemmaList() {

        dilemmaListe = new ArrayList<Dilemma>();
        dilemmaListe.add(new Dilemma("Hjælp til bukser", "Hjælp mig med hvilke bukser jeg skal tage på.", "Hobby", 3, 10 ));
        dilemmaListe.add(new Dilemma("Hjælp til kjoler","Hvilken kjole skal jeg købe til bryllupet?", "Fest" , 2, 10 ));
        dilemmaListe.add(new Dilemma("Skal hunden dø?","Skal min kræftsyge hund aflives?","Personlig", 5, 60 ));
        dilemmaListe.add(new Dilemma("Hvornår skal jeg sige stop?", "Min kone bliver ved med at skifte mine ting i lejligheden ud med hendes.. Hvad skal jeg gøre?", "Personligt", 3, 20));

    }


    public ArrayList<String> getOverskrifter() {
        ArrayList<String> overskrifter = new ArrayList<>();
        for(Dilemma i:dilemmaListe){
            overskrifter.add(i.getTitle());
        }
        return overskrifter;
    }

    public Dilemma getDilemmaListe(int posi) { return dilemmaListe.get(posi); }
    public void addDilemma(Dilemma dilemma) {
        dilemmaListe.add(dilemma);
    }

}
