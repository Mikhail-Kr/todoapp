package com.example.todoapp.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todoapp.dbClasses.EditTaskDbMethods;
import com.example.todoapp.dbClasses.StepDbMethods;
import com.example.todoapp.dbClasses.TaskListDbMethods;
import com.example.todoapp.dbClasses.TaskListsDbMethods;
import com.example.todoapp.R;
import com.example.todoapp.models.DatabaseHelper;
import com.example.todoapp.models.Pictures;
import com.example.todoapp.models.Step;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskList;

public class EditTaskActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = RandomString();
    Uri selectedImage;
    String[] lists = TaskListsDbMethods.selectTaskListsName();
    String item;
    ArrayList<TaskList> taskLists = TaskListsDbMethods.select();
    ArrayList<Task> taskList = TaskListDbMethods.select();
    Step step;
    ArrayList<Step> steps = new ArrayList<>();
    Task task;
    String result;

    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        currentDateTime = (TextView) findViewById(R.id.currentDateTime);

        result = getIntent().getAction();

        // отрисовка спинера
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
        //конец отрисоки спинера
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (result != null && !result.equals("main") && !result.equals("taskList")) {
            if (taskList != null) {
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getName().equals(result)) {
                        task = taskList.get(i);
                    }
                }
            }

            if (task != null) {
                EditText content = findViewById(R.id.list_name);
                content.setText(task.getName());
                EditText content2 = findViewById(R.id.message);
                content2.setText(task.getDisc());
                StepDbMethods.showStepLists(this, this, task.getPrimaryKey());
                Uri uri = task.getPicPath();
                ImageView imageView = findViewById(R.id.printedPic);
                imageView.setImageURI(uri);


                String[] lists = TaskListsDbMethods.getNameFromPK(TaskListDbMethods.selectFK(result));
                //отрисовка спинера с заданным именем
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
                //конец отрисовки спинера
            }
        }
    }

    public void onSaveTask(View view) {
        String actionMain = "main";
        String actionTaskList = "taskList";
        String actionPlanList = "planned";
        if (result.equals(actionMain) || result.equals(actionTaskList) || result.equals(actionPlanList)) {
            EditText content = findViewById(R.id.list_name);
            String name = content.getText().toString();
            EditText content2 = findViewById(R.id.message);
            String disc = content2.getText().toString();
            Uri picPath = selectedImage;
            String dateAlarm = currentDateTime.getText().toString();
            int foreignKey = 0;
            for (int i = 0; i < taskLists.size(); i++) {
                if (taskLists.get(i).toString().equals(item)) {
                    foreignKey = taskLists.get(i).getForeingKey();
                }
            }
            Task task = new Task(name, disc, picPath, 0, dateAlarm, foreignKey);
            EditTaskDbMethods.insertTask(task);
            StepDbMethods.insertSteps(steps);

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            EditText content = findViewById(R.id.list_name);
            String name = content.getText().toString();
            EditText content2 = findViewById(R.id.message);
            String disc = content2.getText().toString();
            Uri picPath = selectedImage;
            String dateAlarm = currentDateTime.getText().toString();
            EditTaskDbMethods.editTask(name, disc, picPath, dateAlarm);
            StepDbMethods.insertSteps(steps);
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
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
        if(result == "taskList" || result == "main") {
            setResult(RESULT_OK, intent);
            finish();
        } else {
            startActivity(intent);
        }
        return true;
    }

    public String RandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return generatedString.concat(".jpg");
    }

    public void addStep(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Имя шага");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            //Сохранение введенного текста в диалог, отображение изменений
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int foreignKey = 0;
                if (taskList.size() == 0) {
                    foreignKey = 1;
                } else {
                    foreignKey = taskList.size() + 1;
                }
                step = new Step(input.getText().toString(), 0, foreignKey);
                steps.add(step);
                StepDbMethods.showStepListsArray(steps, EditTaskActivity.this, EditTaskActivity.this);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    public void addDate(View view) {
        new DatePickerDialog(this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void addTime(View view) {
        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}