package model;

import java.util.ArrayList;
import java.util.Date;

import diverse.App;
import petersen.simon.dilemma.HovedAktivitet;

/**
 * Created by Simon on 19/01/16.
 */
public class Logik {

    public static DilemmaListe dilemmaListe, egneDilemmaer, besvaredeDilemmaer, aktiveDilemmaer;
    public static Dilemma oprettetDilemma, valgtDilemma;
    public static ArrayList<BesvarelseListe> besvarelser;

    public static void setEgneDilemmaer() {
        egneDilemmaer = new DilemmaListe();
        for (Dilemma d : dilemmaListe.getDilemmaListe()) {
            if (d.getOpretterID().equals(App.userID)) egneDilemmaer.addDilemma(d);
        }
    }

    public static void setBesvaredeDilemmaer() {
        besvaredeDilemmaer = new DilemmaListe();
        for (BesvarelseListe besvarelseListe : besvarelser) {
            if (besvarelseListe.getBesvarerID().contains(App.userID))
                besvaredeDilemmaer.addDilemma(dilemmaListe.getDilemma(besvarelseListe.getDilemmaID()));
        }
    }

    public static void tilføjBesvarelse(int dilemmaID, int valgtSvarmulighed, String kommentar) {
        for (BesvarelseListe b : besvarelser)
            if (b.getDilemmaID() == dilemmaID) {
                b.tilføjBesvarelse(valgtSvarmulighed, kommentar, App.userID);
                App.besvarelseFirebaseRef.child(String.valueOf(dilemmaID)).setValue(b);
            }
    }

    public static BesvarelseListe getBesvarelsesListe(int dilemmaID) {
        for (BesvarelseListe liste : besvarelser) {
            if (liste.getDilemmaID() == dilemmaID) return liste;
        }
        return null;
    }

    public static int getNytDilemmaID() {
        //Finder sidste DilemmaID i listen.
        Dilemma dilemma = dilemmaListe.getDilemmaListe().get(dilemmaListe.getDilemmaListe().size()-1);
        return dilemma.getDilemmaID()+1;
    }

    public static BesvarelseListe getBesvarelser(int dilemmaID) {
        for (BesvarelseListe besvarelseListe : besvarelser) {
            if (besvarelseListe.getDilemmaID() == dilemmaID) return besvarelseListe;
        }
        return null;
    }

    public static void setAktiveDilemmaer() {
        aktiveDilemmaer = new DilemmaListe();
        for (Dilemma d : dilemmaListe.getDilemmaListe()) {
            if (d.erAktivt(new Date().getTime())) aktiveDilemmaer.addDilemma(d);
        }
    }

    public static void sætTilbagePil(Boolean input){
        HovedAktivitet.mNavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(input);
    }
}
