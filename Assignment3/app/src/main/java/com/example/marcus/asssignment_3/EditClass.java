package com.example.marcus.asssignment_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditClass extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputNum;
    TextView editStudents;
    //EditText inputStudents;
    static String TAG = "EditClass";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editclass);
        Log.i(TAG, "onCreate");


        inputName = findViewById(R.id.editName);
        inputNum = findViewById(R.id.editNum);
        editStudents = findViewById(R.id.editStudents);
        Button btnAddStudent = findViewById(R.id.btnAddStudent);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        // Receiving the Data
        final String name = i.getStringExtra("editName");
        final int num = i.getIntExtra("editNum", 0);
        final int index = i.getIntExtra("editIndex", 0);
        final String students = ListClass.items.get(index).printStudents();
        Log.i(TAG, "TEST TEST TEST " + students);
        inputName.setText(name);
        inputNum.setText("" + num);
        editStudents.setText(students);

        //AddStudent Button
        btnAddStudent.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(EditClass.this, AddStudent.class);
                nextScreen.putExtra("addStudentName", name);
                nextScreen.putExtra("addStudentNum", num);
                nextScreen.putExtra("addStudentIndex", index);
                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(nextScreen);
                Log.i(TAG, "After startActivity");

            }
        });

        //Save Button
        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                //Starting a new Intent
                Intent nextScreen = new Intent(EditClass.this, ListClass.class);

                String name = inputName.getText().toString();
                String num = inputNum.getText().toString();
                int newNum = Integer.parseInt(num);
                ListClass.items.get(index).setName(name);
                ListClass.items.get(index).setNum(newNum);
                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(nextScreen);
                Log.i(TAG, "After startActivity");
            }

        });

        //Delete Button
        btnDelete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                ListClass.items.remove(index);

                Intent nextScreen = new Intent(EditClass.this, ListClass.class);
                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(nextScreen);
                Log.i(TAG, "After startActivity");
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