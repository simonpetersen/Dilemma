package model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import diverse.App;
import petersen.simon.dilemma.OpretDilemma1Titel_frag;

/**
 * Created by Blumen on 19-01-2016.
 */
public class KameraLogik {

    private String mCurrentPhotoPath;

    public File createImageFile() throws IOException {
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

    public void setPic() {
        // Get the dimensions of the View
        int targetW = OpretDilemma1Titel_frag.selected.getWidth();
        int targetH = OpretDilemma1Titel_frag.selected.getHeight();

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
        OpretDilemma1Titel_frag.selected.setImageBitmap(bitmap);
    }



    //Metode der skifter Uri ud i ArrayList, hvis et billeder ændres.
    public void addUri(ImageView selected, Uri uri)
    {
        if (selected.getTag().equals(OpretDilemma1Titel_frag.emptyImageViewTag)) {
            App.imgUris.add(uri);
        } else {
            App.imgUris.remove(selected.getTag());
            App.imgUris.add(uri);
        }
        selected.setTag(uri);
    }

}
