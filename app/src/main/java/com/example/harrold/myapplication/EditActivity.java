package com.example.harrold.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextCost;
    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextName = (EditText) findViewById(R.id.name_edit_text2);
        editTextDate = (EditText) findViewById(R.id.date_edit_text2);
        editTextCost = (EditText) findViewById(R.id.cost_edit_text2);
        editTextComment = (EditText) findViewById(R.id.comment_edit_text2);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //mToolbar.setTitle(bundle.getString);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

            }
        });
    }
}
