package com.example.marcus.assignment5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class AddFilm extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputDate;
    EditText inputFile;
    static String TAG = "AddFilm";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfilm);
        Log.i(TAG, "onCreate");


        inputName = findViewById(R.id.name);
        inputDate = findViewById(R.id.date);
        inputFile= findViewById(R.id.file);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnClear = findViewById(R.id.btnClear);

        //Insert Button
        btnInsert.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(AddFilm.this, DatabaseScreen.class);

                String name = inputName.getText().toString();
                String date = inputDate.getText().toString();
                String file = inputFile.getText().toString();
                DatabaseScreen.films.add(new Film(name, date, file));
                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(nextScreen);
                Log.i(TAG, "After startActivity");

            }
        });

        //Clear Button
        btnClear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                inputName.setText("");
                inputDate.setText("");
                inputFile.setText("");
            }

        });


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.i(TAG, "onDestroy");

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, "onResume");

    }

}