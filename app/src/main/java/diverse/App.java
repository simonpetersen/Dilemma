package diverse;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.firebase.client.Firebase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
    public static Firebase myFirebaseRef;

    @Override
    public void onCreate() {
        super.onCreate();
        dilemmaListe = new DilemmaListe();
        oprettetDilemma = new Dilemma();
        oprettetDilemma.setDilemmaID(dilemmaListe.getDilemmaListe().size()+1);
        resource = App.this.getResources();
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://dilemma-g41.firebaseio.com/");
        //Tilføje det første element som element nummer 6.
        //myFirebaseRef.child("v0").child(String.valueOf(5)).setValue(dilemmaListe.getDilemmaListe().get(0));

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
                    //oprettetDilemma.addBilledeUrl(result.get("url").toString());
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
}
