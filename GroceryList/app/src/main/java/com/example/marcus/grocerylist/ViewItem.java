package com.example.marcus.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewItem extends Activity {

    static String TAG = "ViewItem";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewitem);
        Log.i(TAG, "onCreate");

        TextView txtName = findViewById(R.id.viewName);
        TextView txtQuantity = findViewById(R.id.viewQuantity);
        TextView txtCatagory = findViewById(R.id.viewCatagory);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        // Receiving the Data
        final long id = i.getLongExtra("id", 0);
        final String name = i.getStringExtra("name");
        final int quantity = i.getIntExtra("quantity", 0);
        final String catagory = i.getStringExtra("catagory");

        // Displaying Received data
        txtName.setText(name);
        txtQuantity.setText("Quantity: " + quantity);
        txtCatagory.setText("Catagory: " + catagory);

        // Update Button
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent nextScreen = new Intent(ViewItem.this, EditItem.class);
                Log.i(TAG, "Before startActivity");
                nextScreen.putExtra("name", name);
                nextScreen.putExtra("quantity",quantity);
                nextScreen.putExtra("catagory", catagory);
                nextScreen.putExtra("id", id);
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
