package diverse;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import model.BesvarelseListe;
import model.Dilemma;
import model.DilemmaListe;
import model.Logik;
import petersen.simon.dilemma.R;

/**
 * Created by Simon on 05/01/16.
 */
public class App extends Application {

    public static Resources resource;
    public static Cloudinary cloudinary;
    public static Firebase dilemmaFirebaseRef, besvarelseFirebaseRef;
    public static String userID, fejlBesked, opretBrugerResultat;
    public static Runnable netværksObservatør, opretDilemmaRun;
    public static ArrayList<Uri> imgUris;
    public static int antalBillederTilUpload;
    public static SharedPreferences prefs;
    public static String prefKey = "Dilemma-UserID";
    public static String cloudinaryURL;

    @Override
    public void onCreate() {
        super.onCreate();
        cloudinaryURL = "http://res.cloudinary.com/dilemma/image/upload/q_50/";
        model.Logik.oprettetDilemma = new Dilemma();
        fejlBesked = null;
        imgUris = new ArrayList<>();
        prefs = getSharedPreferences("DilemmaPref", Context.MODE_PRIVATE);
        userID = prefs.getString(prefKey, null);
        System.out.println(userID);
        resource = App.this.getResources();
        Firebase.setAndroidContext(this);
        dilemmaFirebaseRef = new Firebase("https://dilemma-g41.firebaseio.com/").child("dilemmaListe");
        besvarelseFirebaseRef = new Firebase("https://dilemma-g41.firebaseio.com/").child("besvarelsesListe");

        besvarelseFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                model.Logik.besvarelser = new ArrayList<>();
                Iterable<DataSnapshot> i = dataSnapshot.getChildren();
                for (DataSnapshot data : i) {
                    BesvarelseListe besvarelseListe = data.getValue(BesvarelseListe.class);
                    model.Logik.besvarelser.add(besvarelseListe);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Read error = " + firebaseError.getMessage());
            }
        });

        dilemmaFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                model.Logik.dilemmaListe = new DilemmaListe();
                Iterable<DataSnapshot> i = dataSnapshot.getChildren();
                for (DataSnapshot data : i) {
                    Dilemma dilemma = data.getValue(Dilemma.class);
                    model.Logik.dilemmaListe.addDilemma(dilemma);
                }
                if (netværksObservatør != null) netværksObservatør.run();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Read error = " + firebaseError.getMessage());
                if (netværksObservatør != null) netværksObservatør.run();

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
                    String url = "v"+result.get("version")+"/"+result.get("public_id")+"."+result.get("format");
                    System.out.println(url);
                    Logik.oprettetDilemma.addBilledeUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Upload færdig!");
                return null;
            }

            @Override
        protected void onPostExecute(Object result){
                antalBillederTilUpload --;
                if(antalBillederTilUpload == 0)
                    opretDilemmaRun.run();
            }
        }.execute();
    }

    public static void downloadBillede(String url, ImageView iv) {
        final String urlString = cloudinaryURL + url;
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
        dilemmaFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                App.userID = authData.getUid();
                prefs.edit().putString(prefKey, App.userID).commit();
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
        //dilemmaFirebaseRef.unauth();
        userID = null;
        prefs.edit().putString(prefKey, null).commit();
        System.out.println("Du er nu logget ud!");
    }

    public static void createUser(String email, final String password) {
        dilemmaFirebaseRef.createUser(email, password, new Firebase.ResultHandler() {
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


}
