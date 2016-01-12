package diverse;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Besvarelse;
import model.BesvarelseListe;
import model.Dilemma;
import model.DilemmaListe;
import petersen.simon.dilemma.R;

/**
 * Created by Simon on 05/01/16.
 */
public class App extends Application {

    public static DilemmaListe dilemmaListe;
    public static Dilemma oprettetDilemma;
    public static ArrayList<BesvarelseListe> besvarelser;
    public static Resources resource;
    public static Cloudinary cloudinary;
    public static Firebase myFirebaseRef, besvarelseFirebaseRef;
    public static String userID, fejlBesked, opretBrugerResultat;
    public static Runnable netværksObservatør, splash;

    @Override
    public void onCreate() {
        super.onCreate();
        oprettetDilemma = new Dilemma();
        besvarelser = new ArrayList<>();
        userID = null;
        fejlBesked = null;
        resource = App.this.getResources();
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://dilemma-g41.firebaseio.com/").child("dilemmaListe");
        besvarelseFirebaseRef = new Firebase("https://dilemma-g41.firebaseio.com/").child("besvarelsesListe");

        //Der læses fra Firebase.
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dilemmaListe = new DilemmaListe();
                Iterable<DataSnapshot> i = dataSnapshot.getChildren();
                for (DataSnapshot d : i) {
                    Dilemma dilemma = d.getValue(Dilemma.class);
                    besvarelser.add(new BesvarelseListe(dilemma.getDilemmaID()));
                    dilemmaListe.addDilemma(dilemma);
                }
                if (splash != null) splash.run();
                //besvarelseFirebaseRef.setValue(besvarelser);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Read error = " + firebaseError.getMessage());
                if (splash != null) splash.run();

            }
        });

        //Cloudinary-kald her! Referencer til values-string i res-mappen
        Map config = new HashMap();
        config.put("cloud_name", resource.getString(R.string.cloudinary_cloud_name));
        config.put("api_key", resource.getString(R.string.cloudinary_api_key));
        config.put("api_secret", resource.getString(R.string.cloudinary_api_secret));
        cloudinary = new Cloudinary(config);
    }


    public static void uploadBilled(final InputStream is, String publicID) {
        final String id = publicID;
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    Map result = cloudinary.uploader().upload(is, ObjectUtils.asMap("public_id", id));
                    System.out.println(result.get("url"));
                    oprettetDilemma.addBilledeUrl(result.get("url").toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Upload færdig!");
                return null;
            }
        }.execute();
    }

    public static void downloadBillede(String url, ImageView iv) {
        final String urlString = url;
        final ImageView imageView = iv;
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    URL urlConnection = new URL(urlString);
                    InputStream input = urlConnection.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
        protected void onPostExecute(Object result) {
                imageView.setImageBitmap((Bitmap) result);
            }
        }.execute();
    }

    public static void login(String email, String password) {
        myFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                App.userID = authData.getUid();
                System.out.println("Logged in = " + App.userID);
                if (netværksObservatør != null) netværksObservatør.run();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                App.fejlBesked = firebaseError.getMessage();
                System.out.println(firebaseError.getMessage());
                if (netværksObservatør != null) netværksObservatør.run();
            }
        });
    }

    public static void logout(){
        //myFirebaseRef.unauth();
        userID = null;
        System.out.println("Du er nu logget ud!");
    }

    public static void createUser(String email, final String password) {
        myFirebaseRef.createUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                opretBrugerResultat = "Success!";
                if (netværksObservatør != null) netværksObservatør.run();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                opretBrugerResultat = firebaseError.getMessage();
                if (netværksObservatør != null) netværksObservatør.run();
            }
        });
    }

    public static DilemmaListe getEgneDilemmaer() {
        DilemmaListe liste = new DilemmaListe();
        if (userID == null) return liste;
        for (Dilemma d : dilemmaListe.getDilemmaListe()) {
            if (d.getOpretterID().equals(userID)) liste.addDilemma(d);
        }
        return liste;
    }

    public static void tilføjBesvarelse(Besvarelse besvarelse, int dilemmaID) {
        for (BesvarelseListe l : besvarelser) {
            if (l.getDilemmaID() == dilemmaID) {
                l.addBesvarelse(besvarelse);
                return;
            }
        }
    }
}
