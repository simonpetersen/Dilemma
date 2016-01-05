package petersen.simon.dilemma;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import model.Dilemma;

/**
 * Created by Simon on 24/11/15.
 */
public class OpretDilemma1Titel_frag extends Fragment implements View.OnClickListener {

    private EditText titleEdit, descEdit;
    private ImageView img1, img2, img3, img4, selected;
    private Button detailsButton;
    public final static int PICK_PHOTO_CODE = 1046;

    static Dilemma newDilemma;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_titel_frag, container, false);
        //Opsætning af views.

        if (newDilemma == null)
            newDilemma = new Dilemma();

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
            newDilemma.setTitel(titleEdit.getText().toString());
            newDilemma.setBeskrivelse(descEdit.getText().toString());

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new OpretDilemma2Kategori_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (v == img1 || v == img2 || v == img3 || v == img4) {
            onPickPhoto(v);
            selected = (ImageView) v;
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
            Uri photoUri = data.getData();
            // Do something with the photo based on Uri
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Load the selected image into a preview
            selected.setImageBitmap(selectedImage);
        }
    }
}
