package com.sayed.learnigretrofitlearning;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    public static final String TITLE_EXTRA = "com.sayed.learnigretrofitlearning.TITLE_EXTRA";
    public static final String TEXT_EXTRA = "com.sayed.learnigretrofitlearning.TEXT_EXTRA";
    public static final String PRIORITY_EXTRA = "com.sayed.learnigretrofitlearning.PRIORITY_EXTRA";
    private EditText edit_text_title;
    private EditText edit_text_description;
    private TextView textView ;
    private NumberPicker number_picker_priority;
    private Button save_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initItems();
        initListeners();
    }

    private void initListeners() {
        save_button.setOnClickListener( l -> {
           save_Note();
        });
    }

    private void save_Note() {
        String title = edit_text_title.getText().toString() ;
        String description =edit_text_description.getText().toString();
        int number = number_picker_priority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this , "Please input Value", Toast.LENGTH_LONG).show();
        }

        Intent resultBundle = new Intent();
        resultBundle.putExtra(TITLE_EXTRA , title);
        resultBundle.putExtra(TEXT_EXTRA , description);
        resultBundle.putExtra(PRIORITY_EXTRA , number);
        setResult(RESULT_OK , resultBundle);
        finish();

    }

    private void initItems() {
        this.edit_text_description = findViewById(R.id.edit_text_description);
        this.edit_text_title = findViewById(R.id.edit_text_title);
        this.number_picker_priority = findViewById(R.id.number_picker_priority);
        this.save_button = findViewById(R.id.save_button);

        number_picker_priority.setMinValue(0);
        number_picker_priority.setMaxValue(10);
    }
}