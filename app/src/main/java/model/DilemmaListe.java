package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaListe {

    private ArrayList<Dilemma> dilemmaList;
    private ArrayList<String> svarmuligheder;
    private int valgtDilemmaPosition;


    public DilemmaListe() {
        dilemmaList = new ArrayList<Dilemma>();
        svarmuligheder = new ArrayList<String>();
        svarmuligheder.add("Grønne"); svarmuligheder.add("Blå");
        dilemmaList.add(new Dilemma("Hjælp til bukser", "Hjælp mig med hvilke bukser jeg skal tage på.", "Hobby", 3,
                new Date().getTime()+10*60*1000,true, svarmuligheder));

        svarmuligheder = new ArrayList<String>();
        svarmuligheder.add("Rød"); svarmuligheder.add("Blå");
        dilemmaList.add(new Dilemma("Hjælp til kjoler", "Hvilken kjole skal jeg købe til bryllupet?", "Fest", 2,
                new Date().getTime()+10*60*1000,false, svarmuligheder));

        svarmuligheder = new ArrayList<String>();
        svarmuligheder.add("Dø"); svarmuligheder.add("Lev");
        dilemmaList.add(new Dilemma("Skal hunden dø?", "Skal min kræftsyge hund aflives?", "Personligt", 5,
                new Date().getTime()+60*60*1000,true, svarmuligheder));

        svarmuligheder = new ArrayList<String>();
        svarmuligheder.add("Sig stop!"); svarmuligheder.add("Lad hende bare bestemme!");
        dilemmaList.add(new Dilemma("Hvornår skal jeg sige stop?",
                "Min kone bliver ved med at skifte mine ting i lejligheden ud med hendes.. Hvad skal jeg gøre?", "Personligt", 3,
                new Date().getTime()+20*60*1000,false, svarmuligheder));

        svarmuligheder = new ArrayList<String>();
        svarmuligheder.add("Kølhal ham!"); svarmuligheder.add("Play it safe.. Hold dig i ro og undgå at vække opsigt."); svarmuligheder.add("Hyr en lejemorder..");
        dilemmaList.add(new Dilemma("Masokistisk Høvding!", "Vores Høvding er ond og brutal. Han slår ned på ALLE der begår fejl.",
                "karriere", 5, new Date().getTime()+60*60*1000,true, svarmuligheder));
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
