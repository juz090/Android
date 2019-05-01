package com.example.marcus.assignment4;

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

public class NewRestaurant extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputPhone;
    EditText inputWebsite;
    RatingBar inputRating;
    Spinner inputCatagory;
    static String TAG = "NewRestaurant";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrestaurant);
        Log.i(TAG, "onCreate");


        inputName = findViewById(R.id.name);
        inputPhone = findViewById(R.id.phone);
        inputWebsite = findViewById(R.id.website);
        inputRating = findViewById(R.id.rating);
        inputCatagory = findViewById(R.id.catagory);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnClear = findViewById(R.id.btnClear);

        String[] items = new String[]{"", "Mexican", "American", "Italian", "French", "Asian"};
        ArrayAdapter<String> catagoryItems = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        inputCatagory.setAdapter(catagoryItems);

        //Insert Button
        btnInsert.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(NewRestaurant.this, ListRestaurant.class);

                String name = inputName.getText().toString();
                String phone = inputPhone.getText().toString();
                String website = inputWebsite.getText().toString();
                int rating = (int)inputRating.getRating();
                String catagory = inputCatagory.getSelectedItem().toString();
                ListRestaurant.restaurants.add(new Restaurant(name, phone, website, rating, catagory));
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
                inputPhone.setText("");
                inputWebsite.setText("");
                inputRating.setRating(0);
                inputCatagory.setSelection(0);
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