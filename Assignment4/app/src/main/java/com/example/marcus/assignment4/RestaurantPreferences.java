package com.example.marcus.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantPreferences extends Activity {

    static String TAG = "RestaurantPreferences";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantpreferences);
        Log.i(TAG, "onCreate");

        final CheckBox checkPhone = findViewById(R.id.phone);
        final CheckBox checkWebsite = findViewById(R.id.website);
        final CheckBox checkCatagory = findViewById(R.id.catagory);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        final boolean phone = ListRestaurant.preferences[0];
        final boolean website = ListRestaurant.preferences[1];
        final boolean catagory = ListRestaurant.preferences[2];

        // Displaying Received data
        checkPhone.setChecked(phone);
        checkWebsite.setChecked(website);
        checkCatagory.setChecked(catagory);


        // Update Button
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent nextScreen = new Intent(RestaurantPreferences.this, ListRestaurant.class);
                //ListRestaurant.preferences[0] = checkPhone.isChecked();
               // ListRestaurant.preferences[1] = checkWebsite.isChecked();
                //ListRestaurant.preferences[2] = checkCatagory.isChecked();
                Log.i(TAG, "Before startActivity");
                Toast.makeText(RestaurantPreferences.this, "Preferences Updated", Toast.LENGTH_SHORT).show();
                // starting new activity
                startActivity(nextScreen);
                Log.i(TAG, "After startActivity");
            }
        });


    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        if (view.getId() == R.id.phone) {
            if (checked)
                ListRestaurant.preferences[0] = true;
            else
                ListRestaurant.preferences[0] = false;
        }
        else if (view.getId() == R.id.website) {
            if (checked)
                ListRestaurant.preferences[1] = true;
            else
                ListRestaurant.preferences[1] = false;
        }
        else if (view.getId() == R.id.catagory) {
            if (checked)
                ListRestaurant.preferences[2] = true;
            else
                ListRestaurant.preferences[2] = false;
        }
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


