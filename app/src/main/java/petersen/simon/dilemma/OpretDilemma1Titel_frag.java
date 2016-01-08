package petersen.simon.dilemma;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private final static int PICK_PHOTO_CODE = 1046;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mCurrentPhotoPath;
    private ArrayList<Uri> imgUris;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_titel_frag, container, false);
        //Opsætning af views.

        imgUris = new ArrayList<Uri>();

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

            uploadBilleder(); //Alle valgte billeder uploades til cloudinary.

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new OpretDilemma2Kategori_frag())
                    .addToBackStack(null)
                    .commit();
        } else if ( v == img1 || v == img2 || v == img3 || v == img4) {
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
        System.out.println("resultcode" + resultCode);
        ContentResolver cr = getActivity().getContentResolver();
        if (data != null) {
            Bitmap image = null;
            Uri photoUri = data.getData();
            try {
                image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            selected.setImageBitmap(image);
            imgUris.add(photoUri);
                /*
                final InputStream inputStream = is;
                */
            //Billede upload
        } else {
            setPic();
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

                System.out.println("photfile !=null");
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
        System.out.println(image.getName());
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        System.out.println("currenphotopath "+mCurrentPhotoPath);
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
        imgUris.add(Uri.fromFile(new File(mCurrentPhotoPath)));
    }

    private void uploadBilleder()
    {
        for (Uri uri : imgUris) {
            InputStream is = null;
            try {
                is = getActivity().getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            App.uploadBilled(is, uri.toString());
        }
    }

}