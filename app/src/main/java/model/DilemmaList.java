package model;

import java.util.ArrayList;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaList {

    ArrayList<String> overskrifter;
    ArrayList<String> beskrivelser;

    public DilemmaList() {
        overskrifter = new ArrayList<String>();
        overskrifter.add("Hjælp til bukser");
        overskrifter.add("Hjælp til kjoler");
        overskrifter.add("Skal hunden dø?");
        overskrifter.add("Hvornår skal jeg sige stop?");

        beskrivelser = new ArrayList<String>();
        beskrivelser.add("Hjælp mig med hvilke bukser jeg skal tage på.");
        beskrivelser.add("Hvilken kjole skal jeg købe til bryllupet?");
        beskrivelser.add("Skal min kræftsyge hund aflives?");
        beskrivelser.add("Min kone bliver ved med at skifte mine ting i lejligheden ud med hendes.. Hvad skal jeg gøre?");
    }

    public void addFirstDilemma(String overskrift, String beskrivelse) {
        overskrifter.add(0, overskrift);
        beskrivelser.add(0, beskrivelse);
    }

    public ArrayList<String> getOverskrifter() { return overskrifter; }

    public ArrayList<String> getBeskrivelser() { return beskrivelser; }
}
