package petersen.simon.dilemma;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

import java.io.File;
import java.io.IOException;
import java.util.Map;

import diverse.App;

/**
 * Created by Simon on 24/11/15.
 */
public class OpretDilemma1Titel_frag extends Fragment implements View.OnClickListener {

    private EditText titleEdit, descEdit;
    private ImageView img1, img2, img3, img4;
    private ImageView selected;
    private Button detailsButton;
    public final static int PICK_PHOTO_CODE = 1046;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_titel_frag, container, false);
        //Opsætning af views.

        titleEdit = (EditText) v.findViewById(R.id.titleEdit);
        descEdit = (EditText) v.findViewById(R.id.descEdit);
        detailsButton = (Button) v.findViewById(R.id.detaljerButton);
        detailsButton.setOnClickListener(this);

        img1 = (ImageView) v.findViewById(R.id.imageView1);
        img2 = (ImageView) v.findViewById(R.id.imageView2);
        img3 = (ImageView) v.findViewById(R.id.imageView3);
        img4 = (ImageView) v.findViewById(R.id.imageView4);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
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
        } else if ( v == img1 || v == img2 || v == img3 || v == img4) {
            /*
            onPickPhoto(v);
            selected = (ImageView) v;
            */
            final View view = v;
            selected = (ImageView) view;
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
        super.onActivityResult(requestCode, resultCode, data);
        ContentResolver cr = getActivity().getContentResolver();
        if (data != null) {
            Bitmap image = null;
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                image = (Bitmap) extras.get("data");
                final File file = new File(image.toString());
                new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] params) {
                        try {
                            App.cloudinary.uploader().upload(file, ObjectUtils.asMap("id", "dilemma1"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();

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
            final Bitmap image1 = image;

            /*
            new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    Map m;
                    try {
                        m = App.cloudinary.uploader().upload("image1", ObjectUtils.asMap("dilemma_id", "image_"));
                        System.out.println("KIG HER!!!: " + m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();
            */
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
