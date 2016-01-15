package petersen.simon.dilemma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import diverse.App;

/**
 * Created by Simon on 24/11/15.
 */
public class OpretDilemma1Titel_frag extends Fragment implements View.OnClickListener {

    private EditText titleEdit, descEdit;
    private ImageView selected;
    private Button detailsButton;
    private final static int PICK_PHOTO_CODE = 1046;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mCurrentPhotoPath, emptyImageViewTag;
    private ArrayList<ImageView> imgViews;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_titel_frag, container, false);
        HovedAktivitet.mNavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(true);

        //Opsætning af views.

        emptyImageViewTag = "tomt";
        imgViews = new ArrayList<ImageView>();

        imgViews.add((ImageView) v.findViewById(R.id.imageView1));
        imgViews.add((ImageView) v.findViewById(R.id.imageView2));
        imgViews.add((ImageView) v.findViewById(R.id.imageView3));
        imgViews.add((ImageView) v.findViewById(R.id.imageView4));


        for (ImageView iv : imgViews) {
            iv.setOnClickListener(this);
            iv.setTag(emptyImageViewTag);
        }

        titleEdit = (EditText) v.findViewById(R.id.titleEdit);
        descEdit = (EditText) v.findViewById(R.id.descEdit);
        detailsButton = (Button) v.findViewById(R.id.detaljerButton);
        detailsButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == detailsButton) {

            //detailsButton.setBackgroundColor(Color.GREEN);

            if (checkInputTitel()) {
                App.oprettetDilemma.setTitel(titleEdit.getText().toString());
                App.oprettetDilemma.setBeskrivelse(descEdit.getText().toString());

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new OpretDilemma2Kategori_frag())
                        .addToBackStack(null)
                        .commit();
            }
        } else if (imgViews.contains(v)) {
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
        if (data != null) {
            Bitmap image = null;
            Uri photoUri = data.getData();
            System.out.println(photoUri);
            try {
                image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            selected.setImageBitmap(image);
            addUri(selected, photoUri);
        } else {
            setPic();
            addUri(selected, Uri.fromFile(new File(mCurrentPhotoPath)));
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        final File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = selected.getWidth();
        int targetH = selected.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        selected.setImageBitmap(bitmap);
    }

    //Metode der tjekker input.
    //Retunere true hvis der er givet titel.
    private boolean checkInputTitel() {
        if (titleEdit.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Du mangler at give en titel.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //Metode der skifter Uri ud i ArrayList, hvis et billeder ændres.
    private void addUri(ImageView selected, Uri uri)
    {
        if (selected.getTag().equals(emptyImageViewTag)) {
            App.imgUris.add(uri);
        } else {
            App.imgUris.remove(selected.getTag());
            App.imgUris.add(uri);
        }
        selected.setTag(uri);
    }

}