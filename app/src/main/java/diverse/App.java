package diverse;

import android.app.Application;
import android.content.res.Resources;
//import android.support.v7.widget.ResourcesWrapper;

import com.cloudinary.Cloudinary;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import model.Dilemma;
import model.DilemmaListe;
import petersen.simon.dilemma.R;

/**
 * Created by Simon on 05/01/16.
 */
public class App extends Application {

    public static DilemmaListe dilemmaListe;
    public static Dilemma oprettetDilemma;
    public static Resources resource;
    public static Cloudinary cloudinary;

    @Override
    public void onCreate() {
        super.onCreate();
        dilemmaListe = new DilemmaListe();
        oprettetDilemma = new Dilemma();
        resource = App.this.getResources();

        //Cloudinary-kald her!
        Map config = new HashMap();
        config.put("cloud_name", resource.getString(R.string.cloudinary_cloud_name));
        config.put("api_key", resource.getString(R.string.cloudinary_api_key));
        config.put("api_secret", resource.getString(R.string.cloudinary_api_secret));
        cloudinary = new Cloudinary(config);

        //Firebase-kald her!
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://dilemma-g41.firebaseio.com/");
        myFirebaseRef.child("v0").setValue(dilemmaListe.getDilemmaListe());
    }
}
