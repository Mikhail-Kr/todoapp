package com.example.todoapp.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import android.widget.Spinner;
import android.widget.Toast;

import com.example.todoapp.dbClasses.EditTaskDbMethods;
import com.example.todoapp.dbClasses.TaskListsDbMethods;
import com.example.todoapp.R;
import com.example.todoapp.models.Pictures;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskList;

public class EditTaskActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = RandomString();
    Uri selectedImage;
    String[] lists = TaskListsDbMethods.selectTaskListsName();
    String item;
    ArrayList<TaskList> taskList = TaskListsDbMethods.select();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                item = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    public void onClick(View view) {
        EditText content = findViewById(R.id.list_name);
        String name = content.getText().toString();
        EditText content2 = findViewById(R.id.message);
        String disc = content2.getText().toString();
        Uri picPath = selectedImage;
        int foreignKey = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().equals(item)) {
                foreignKey = taskList.get(i).getForeingKey();
            }
        }
        Task task = new Task(name, disc, picPath, 0, foreignKey);
        EditTaskDbMethods.insert(task);
        Intent intent = new Intent();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
        setResult(RESULT_OK, intent);
        finish();
        return true;
    }

    public String RandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return generatedString.concat(".jpg");
    }

    public void addStep(View view) {
    }
}