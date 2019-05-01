package com.example.marcus.asssignment_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowClass extends Activity {

    static String TAG = "ShowClass";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showclass);
        Log.i(TAG, "onCreate");

        TextView txtName = findViewById(R.id.className);
        TextView txtNum = findViewById(R.id.classNum);
        TextView showStudents = findViewById(R.id.classStudents);
        Button btnEdit = findViewById(R.id.btnEdit);

        Intent i = getIntent();
        // Receiving the Data
        final int index = i.getIntExtra("num", 0);
        final String name = ListClass.items.get(index).getName();
        final int num = ListClass.items.get(index).getNum();
        final String students = ListClass.items.get(index).printStudents();

        // Displaying Received data
        txtName.setText(name);
        txtNum.setText("Class Number: " + num);
        showStudents.setText("Students:\n" + students);

        // Edit Button
        btnEdit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent nextScreen = new Intent(ShowClass.this, EditClass.class);
                nextScreen.putExtra("editName", name);
                nextScreen.putExtra("editNum", num);
                nextScreen.putExtra("editIndex", index);
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
}

