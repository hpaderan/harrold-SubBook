package com.example.harrold.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener, EditDialog.EditDialogListener {

    private static final String FILENAME = "file.sav";
    private ListView oldSubsList;

    private String tempName;
    private String tempDate;
    private String tempCost;
    private String tempComment;
    private float monthlyTotal;

    private ArrayList<Sub> subsList;
    private ArrayAdapter<Sub> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldSubsList = (ListView) findViewById(R.id.oldSubsList);
        Button adderButton = (Button) findViewById(R.id.AdderButton);
        Button editButton = (Button) findViewById(R.id.EditButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                subsList.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        adderButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                openDialog();
                //Intent i = new Intent(MainActivity.this, AddActivity.class);
                //startActivityForResult(i, 1);
                Sub newSub = new Sub(tempName, tempDate, tempCost, tempComment);
                subsList.add(newSub);

                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        /* pulled from https://www.youtube.com/watch?v=wSCIuIbS-nk */
        oldSubsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                //intent.putExtra("Sub", oldSubsList.getItemAtPosition(i));
                intent.putExtra("position", i);
                //startActivity(intent);
                view.setSelected(true);

                openEditDialog();

                subsList.get(i).setName(tempName);
                subsList.get(i).setDateCreated(tempDate);
                subsList.get(i).setCostMonthly(tempCost);
                subsList.get(i).setComment(tempComment);

                adapter.notifyDataSetChanged();
                saveInFile();
                clearTemps();

            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        loadFromFile();
        adapter = new ArrayAdapter<Sub>(MainActivity.this,
                R.layout.list_item, subsList);
        oldSubsList.setAdapter(adapter);
    }

/*
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, Sub.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    } */

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-02-04
            Type listType = new TypeToken<ArrayList<Sub>>(){}.getType();
            subsList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            subsList = new ArrayList<Sub>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
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

    public void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "example_dialog");
    }

    public void openEditDialog() {
        EditDialog editDialog = new EditDialog();
        editDialog.show(getSupportFragmentManager(), "example_dialog2");
    }

    @Override
    public void applyText(String subName, String subDate,
                          String subCost, String subComment) {
        tempName = subName;
        tempDate = subDate;
        tempCost = subCost;
        tempComment = subComment;
    }

    @Override
    public void applyEdit(String subName, String subDate,
                             String subCost, String subComment) {
        tempName = subName;
        tempDate = subDate;
        tempCost = subCost;
        tempComment = subComment;
    }


    /* https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private void clearTemps() {
        tempName = "";
        tempDate = "";
        tempCost = "";
        tempComment = "";
    }
}

