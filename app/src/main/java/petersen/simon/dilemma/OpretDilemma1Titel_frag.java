package petersen.simon.dilemma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

import diverse.App;

/**
 * Created by Simon on 24/11/15.
 */
public class OpretDilemma1Titel_frag extends Fragment implements View.OnClickListener {

    private EditText titleEdit, descEdit;
    private ImageView img1, img2, img3, img4, selected;
    private Button detailsButton;
    public final static int PICK_PHOTO_CODE = 1046;
    AQuery aq;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_titel_frag, container, false);
        //Opsætning af views.

        aq = new AQuery(v);

        //aq.getEditText(R.id.titleEdit);
        titleEdit = (EditText) v.findViewById(R.id.titleEdit);
        descEdit = (EditText) v.findViewById(R.id.descEdit);
        detailsButton = (Button) v.findViewById(R.id.detaljerButton);
        detailsButton.setOnClickListener(this);

        aq.image(R.id.imageView1).click();
        aq.image(R.id.imageView2).click();
        aq.image(R.id.imageView3).click();
        aq.image(R.id.imageView4).click();

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == detailsButton) {
            App.oprettetDilemma.setTitel(titleEdit.getText().toString());
            App.oprettetDilemma.setBeskrivelse(descEdit.getText().toString());

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new OpretDilemma2Kategori_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (v == img1 || v == img2 || v == img3 || v == img4) {
            /*
            onPickPhoto(v);
            selected = (ImageView) v;
            */
            final View view = v;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Opret ny konto?");
            builder.setPositiveButton("Tag billede", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dispatchTakePictureIntent();
                }
            });
            builder.setNegativeButton("Vælg fra galleri", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    onPickPhoto(view);
                    selected = (ImageView) view;
                }
            });
            builder.create().show();
        }
    }

    public void onPickPhoto(View view) {
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bitmap image = null;
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
                Bundle extras = data.getExtras();
                image = (Bitmap) extras.get("data");
            } else {
                Uri photoUri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            selected.setImageBitmap(image);
            //Billede upload
           /* new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    try {
                        Map m = App.cloudinary.uploader().upload("http://www.example.com/image.jpg", ObjectUtils.asMap("public_id", "sample_remote"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();*/
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
