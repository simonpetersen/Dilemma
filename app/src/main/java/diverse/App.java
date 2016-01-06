package diverse;

import android.app.Application;

import com.cloudinary.Cloudinary;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

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

        //Cloudinary-kald her!
        Map config = new HashMap();
        config.put("cloud_name", "n07t21i7");
        config.put("api_key", "123456789012345");
        config.put("api_secret", "abcdeghijklmnopqrstuvwxyz12");
        Cloudinary cloudinary = new Cloudinary(config);

        //Firebase-kald her!
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://dilemma-g41.firebaseio.com/");
        myFirebaseRef.child("v0").setValue(dilemmaListe.getDilemmaListe());
    }
}
