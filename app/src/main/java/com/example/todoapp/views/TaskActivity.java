package com.example.todoapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

import android.widget.Toast;

import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.R;
import com.example.todoapp.models.Pictures;
import com.example.todoapp.models.Task;

public class TaskActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public final String APP_TAG = "ToDoApp";
    public String photoFileName = RandomString();
    Uri selectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    public void onClick(View view) {
        EditText content = findViewById(R.id.name);
        String name = content.getText().toString();
        EditText content2 = findViewById(R.id.message);
        String disc = content2.getText().toString();
        Uri picPath = selectedImage;
        String picName = photoFileName;
        Task task = new Task(name, disc, picPath, picName);
        DatabaseHelper.db.execSQL("INSERT INTO tasks (" + DatabaseHelper.COLUMN_NAME + ", "
                + DatabaseHelper.COLUMN_DISC + " , " + DatabaseHelper.COLUMN_PICS_PATH + " , "
                + DatabaseHelper.COLUMN_PICS_NAME + ") VALUES ('" + task.getName() + "', '"
                + task.getDisc() + "', '" + task.getPicPath() + "', '"+ task.getPicName() +"' );");

        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClick_Image(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    public void onClick_Cam(View view) {
        selectedImage = Pictures.getUri(this, photoFileName);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImage);
        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = findViewById(R.id.printedPic);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_LOAD_IMAGE:
                    selectedImage = data.getData();
                    selectedImage = Pictures.copyImg(this, photoFileName, selectedImage);
                    imageView.setImageURI(selectedImage);

                    break;

                case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                    // by this point we have the camera photo on disk
                    Bitmap takenImage = Pictures.setPic(this, photoFileName, imageView);
                    // RESIZE BITMAP, see section below
                    // Load the taken image into a preview
                    imageView.setImageBitmap(takenImage);
                    break;
            }
        } else { // Result was a failure
            Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
        }

    }

    public String RandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
        return generatedString.concat(".jpg");
    }
}