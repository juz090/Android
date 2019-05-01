package com.example.marcus.asssignment_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListClass extends Activity {

    static String TAG = "ListClass";
    static ArrayList<Class> items = new ArrayList<Class>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listclasses);
        Log.i(TAG, "onCreate");

        ListView listView1 = findViewById(R.id.listClasses);
        ArrayAdapter<Class> adapter = new ArrayAdapter<Class>(this,android.R.layout.simple_list_item_1,items);
        listView1.setAdapter(adapter);

        //List of Classes
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("clicked", (String)((TextView) view).getText());
                Toast.makeText(ListClass.this, items.get(i).toString(), Toast.LENGTH_SHORT).show();

                Intent showClass = new Intent(ListClass.this, ShowClass.class);

                //Sending data to another Activity
                showClass.putExtra("num", i);

                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(showClass);
                Log.i(TAG, "After startActivity");
            }
        });

        //Add Class Button
        Button btnAddClass = findViewById(R.id.btnAddClass);
        // Binding Click event to Button
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent nextScreen = new Intent(ListClass.this, NewClass.class);
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
