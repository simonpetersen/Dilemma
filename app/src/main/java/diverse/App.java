package diverse;

import android.app.Application;
import model.Dilemma;
import model.DilemmaListe;

/**
 * Created by Simon on 05/01/16.
 */
public class App extends Application {

    public static DilemmaListe dilemmaListe;
    public static Dilemma oprettetDilemma;

    @Override
    public void onCreate() {
        super.onCreate();
        dilemmaListe = new DilemmaListe();
        oprettetDilemma = new Dilemma();

        //Firebase-kald her!
    }
}