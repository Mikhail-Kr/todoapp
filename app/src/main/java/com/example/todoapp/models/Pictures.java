package com.example.todoapp.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import java.io.File;

public class Pictures {

    public static Uri getUri(Context context, String photoFileName) {
        Uri selectedImage;
        // create Intent to take a picture and return control to the calling application

        // Create a File reference for future access
        File photoFile = getPhotoFileUri(context, photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(context, "com.example.todoapp", photoFile);
        selectedImage = fileProvider;
        return selectedImage;
    }

    public static Bitmap setPic(Context context, String photoFileName, ImageView imageView) {
        String currentPhotoPath;
        File photoFile = getPhotoFileUri(context, photoFileName);
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        currentPhotoPath = photoFile.getAbsolutePath();
        Uri fileProvider = FileProvider.getUriForFile(context, "com.example.todoapp", photoFile);

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW/targetW, photoH/targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        return bitmap;
    }

    // Returns the File for a photo stored on disk given the fileName
    public static File getPhotoFileUri(Context context, String fileName) {
        final String APP_TAG = "ToDoApp";
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(APP_TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }


}
