package com.example.marcus.grocerylist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewItem extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputQuantity;
    Spinner inputCatagory;
    static String TAG = "NewItem";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newitem);
        Log.i(TAG, "onCreate");


        inputName = findViewById(R.id.name);
        inputQuantity = findViewById(R.id.quantity);
        inputQuantity.setTransformationMethod(null);
        inputCatagory = findViewById(R.id.catagory);
        Button btnIncrement = findViewById(R.id.btnIncrement);
        Button btnDecrement = findViewById(R.id.btnDecrement);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnClear = findViewById(R.id.btnClear);

        String[] items = new String[]{"Grains", "Meat", "Fruit", "Vegetables", "Dairy", "Misc."};
        ArrayAdapter<String> catagoryItems = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        inputCatagory.setAdapter(catagoryItems);

        //Decrement Button
        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity;
                try {
                    quantity = Integer.parseInt(inputQuantity.getText().toString());
                }
                catch (Exception e){
                    quantity = 1;
                }

                if (quantity > 1)
                    quantity--;
                inputQuantity.setText(String.valueOf(quantity));
            }
        });

        //Increment Button
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity;
                try {
                    quantity = Integer.parseInt(inputQuantity.getText().toString());
                }
                catch(Exception e){
                    quantity = 0;
                }
                quantity++;
                inputQuantity.setText(String.valueOf(quantity));
            }
        });

        //Insert Button
        btnInsert.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(NewItem.this, ItemList.class);

                String name = inputName.getText().toString();
                int quantity = Integer.parseInt(inputQuantity.getText().toString());
                String catagory = inputCatagory.getSelectedItem().toString();

                if (name.length() > 0 && catagory.length() > 0) {


                    //ItemList.items.add(new Item(name, quantity, catagory));

                    ContentValues cv = new ContentValues();
                    cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
                    cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, quantity);
                    cv.put(GroceryContract.GroceryEntry.COLUMN_CATAGORY, catagory);

                    ItemList.mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);
                    ItemList.mAdapter.swapCursor(ItemList.getAllItems());

                    Log.i(TAG, "Before startActivity");
                    // starting new activity
                    startActivity(nextScreen);
                    Log.i(TAG, "After startActivity");
                }

            }
        });

        //Clear Button
        btnClear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                inputName.setText("");
                inputQuantity.setText("1");
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
