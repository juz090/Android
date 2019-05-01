package com.example.marcus.assignment4;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.Log;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.util.ArrayList;

public class ListRestaurant extends AppCompatActivity {

    static String TAG = "ListRestaurant";
    static ArrayList<Restaurant> restaurants = new ArrayList<>();
    static Boolean[] preferences = new Boolean[]{true, true, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listrestaurant);
        Toolbar toolbar = findViewById(R.id.screenMenu);
        setSupportActionBar(toolbar);
        Log.i(TAG, "onCreate");

        ListView listRestaurants = findViewById(R.id.listRestaurants);
        ArrayAdapter<Restaurant> restaurantAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restaurants);
        listRestaurants.setAdapter(restaurantAdapter);

        //List of Restaurants
        listRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Log.v("clicked", (String)((TextView) view).getText());

                Intent showClass = new Intent(ListRestaurant.this, ViewRestaurant.class);

                //Sending data to another Activity
                showClass.putExtra("num", i);

                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(showClass);
                Log.i(TAG, "After startActivity");
            }
        });

        //Add Button
        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent addRestaurant = new Intent(ListRestaurant.this, NewRestaurant.class);

                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(addRestaurant);
                Log.i(TAG, "After startActivity");

            }
        });
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.screenMenu) {
            return true;
        }

        if(item.getItemId() == R.id.clear){

            while(!restaurants.isEmpty()){
                restaurants.remove(0);
            }
            Toast.makeText(ListRestaurant.this, "All restaurants are successfully removed", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        }

        if(item.getItemId() == R.id.load){

            InputStream inputStream = null;
            try {
                inputStream = getResources().openRawResource(R.raw.sample);
                //byte[] reader = new byte[inputStream.available()];
                BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream)));
                String str = "";
                int counter = 0;
                ArrayList<String> load = new ArrayList<>();
                if(inputStream != null){
                    while((str = reader.readLine()) != null) {
                        if (!str.equals(""))
                            load.add(str);
                    }
                }

                String name = "Demo";
                String phone = "Demo";
                String website = "Demo";
                String rating = "Demo";
                String catagory = "Demo";
                for (int i = 0; i < load.size(); i = i+5) {
                    name = load.get(i);
                    phone = load.get(i+1);
                    website = load.get(i+2);
                    rating = load.get(i+3);
                    catagory = load.get(i+4);
                    int intRating = Integer.parseInt(rating);
                    restaurants.add(new Restaurant(name, phone, website, intRating, catagory));
                }
                finish();
                startActivity(getIntent());

            }

            catch(IOException e) {
                Log.e(TAG, e.getMessage());
            }

            finally {

                if (inputStream != null) {

                    try {
                        inputStream.close();
                    }

                    catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }

        if(item.getItemId() == R.id.preferences) {
            Intent preferences = new Intent(ListRestaurant.this, RestaurantPreferences.class);
            Log.i(TAG, "Before startActivity");
            // starting new activity
            startActivity(preferences);
            Log.i(TAG, "After startActivity");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
