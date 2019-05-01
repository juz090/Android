package com.example.marcus.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewRestaurant extends Activity {

    static String TAG = "ViewRestaurant";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewrestaurant);
        Log.i(TAG, "onCreate");

        TextView txtName = findViewById(R.id.name);
        TextView txtPhone = findViewById(R.id.phone);
        TextView txtWebsite = findViewById(R.id.website);
        final RatingBar barRating = findViewById(R.id.rating);
        TextView txtCatagory = findViewById(R.id.catagory);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        // Receiving the Data
        final int index = i.getIntExtra("num", 0);
        final String name = ListRestaurant.restaurants.get(index).getName();
        final String phone = ListRestaurant.restaurants.get(index).getPhone();
        final String website = ListRestaurant.restaurants.get(index).getWebsite();
        final int rating = ListRestaurant.restaurants.get(index).getRating();
        final String catagory = ListRestaurant.restaurants.get(index).getCatagory();

        // Displaying Received data
        txtName.setText(name);
        txtPhone.setText("Phone: " + phone);
        Linkify.addLinks(txtPhone, Linkify.ALL);
        txtWebsite.setText("Website: " + website);
        Linkify.addLinks(txtWebsite, Linkify.ALL);
        barRating.setRating(rating);
        txtCatagory.setText("Catagory: " + catagory);

        // Update Button
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent nextScreen = new Intent(ViewRestaurant.this, ListRestaurant.class);
                ListRestaurant.restaurants.get(index).setRating((int)barRating.getRating());
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


