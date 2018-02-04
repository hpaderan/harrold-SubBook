package com.example.harrold.myapplication;

import android.content.Context;
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
    private EditText chargeText;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameText = findViewById(R.id.nameEditText);
        dateText = findViewById(R.id.dateEditText);
        chargeText = findViewById(R.id.chargeEditText);
        commentText = findViewById(R.id.commentEditText);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
/*
                CHECK INPUTS: SAVE

                String text = bodyText.getText().toString();
                saveInFile(text, new Date(System.currentTimeMillis()));  */
            }
        });
    }

    private void saveInFile(String text, Date date) {
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
    }
}
