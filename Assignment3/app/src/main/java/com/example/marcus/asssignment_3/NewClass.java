package com.example.marcus.asssignment_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewClass extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputNum;
    //EditText inputStudents;
    static String TAG = "NewClass";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newclass);
        Log.i(TAG, "onCreate");


        inputName = findViewById(R.id.name);
        inputNum = findViewById(R.id.num);
        //inputStudents = findViewById(R.id.students);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnClear = findViewById(R.id.btnClear);

        //Insert Button
        btnInsert.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(NewClass.this, ListClass.class);

                String name = inputName.getText().toString();
                String num = inputNum.getText().toString();
                int newNum = Integer.parseInt(num);
                ListClass.items.add(new Class(name,newNum));
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
                inputNum.setText("");
                //inputStudents.setText("");
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