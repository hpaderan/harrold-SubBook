package com.example.harrold.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class AddActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText dateText;
    private EditText costText;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameText = findViewById(R.id.nameEditText);
        dateText = findViewById(R.id.dateEditText);
        costText = findViewById(R.id.costEditText);
        commentText = findViewById(R.id.commentEditText);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                String subName = nameText.getText().toString();
                String subDate = dateText.getText().toString();
                String subCost = costText.getText().toString();
                String subComment = nameText.getText().toString();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("subName", subName);
                returnIntent.putExtra("subDate", subDate);
                returnIntent.putExtra("subCost", subCost);
                returnIntent.putExtra("subComment", subComment);
                setResult(Activity.RESULT_OK, returnIntent);

                finish();
            }
        });
    }

 /*   private void saveInFile(String text, Date date) {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subsList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }  */
}
