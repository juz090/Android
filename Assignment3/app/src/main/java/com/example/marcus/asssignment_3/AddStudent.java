package com.example.marcus.asssignment_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddStudent extends Activity {
    // Initializing variables
    EditText inputFirstName;
    EditText inputLastName;
    EditText inputID;
    ImageView image;
    static String TAG = "AddStudent";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);
        Log.i(TAG, "onCreate");


        inputFirstName = findViewById(R.id.firstName);
        inputLastName = findViewById(R.id.lastName);
        inputID = findViewById(R.id.id);
        image = findViewById(R.id.imageView);
        Button btnSave = findViewById(R.id.btnSave);

        image.setImageResource(R.drawable.android);

        Intent i = getIntent();
        final int index = i.getIntExtra("addStudentIndex",0);
        final String name = ListClass.items.get(index).getName();
        final int num = ListClass.items.get(index).getNum();
        //Save Button
        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(AddStudent.this, EditClass.class);

                nextScreen.putExtra("editName", name);
                nextScreen.putExtra("editNum", num);
                nextScreen.putExtra("editIndex", index);

                String firstName = inputFirstName.getText().toString();
                String lastName = inputLastName.getText().toString();
                String id = inputID.getText().toString();
                int newID = Integer.parseInt(id);
                ListClass.items.get(index).addStudent(firstName,lastName,newID);
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