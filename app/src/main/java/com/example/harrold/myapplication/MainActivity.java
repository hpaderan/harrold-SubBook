package com.example.harrold.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView allSubsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allSubsList = (ListView) findViewById(R.id.allSubsList);
        Button adderButton = (Button) findViewById(R.id.AdderButton);
        Button editButton = (Button) findViewById(R.id.EditButton);

        adderButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                /*
                String text = bodyText.getText().toString();
                saveInFile(text, new Date(System.currentTimeMillis()));
                */
                Intent myIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(myIntent);

                finish();

            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        String[] subs = loadFromFile();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, subs);
        allSubsList.setAdapter(adapter);
    }

    private String[] loadFromFile() {
        ArrayList<String> subs = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                subs.add(line);
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subs.toArray(new String[subs.size()]);
    }

    private void saveInFile(String text, Date date) {
        /*
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_APPEND);
            fos.write(new String(date.toString() + " | " + text)
                    .getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
    }
}

/**
 * TOMORROW:
 *
 *      NEED to make the Subs class +
 *
 *      AddButton on main activity:
 *          - change activity
 *
 *      EditButton on main activity:
 *          - redesign
 *
 *      View list +
 *
 *      Save/Load list +
 *      -----------------
 *      ON ACTIVITY 2:
 *
 *      inputs: EditTexts, calendar viewer
 *
 *      AddButton:
 *          - check inputs
 */
