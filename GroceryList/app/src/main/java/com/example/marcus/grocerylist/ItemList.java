package com.example.marcus.grocerylist;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class ItemList extends Activity {

    static String TAG = "ItemList";
    //static ArrayList<Item> items = new ArrayList<>();
    public static GroceryAdapter mAdapter;
    static SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Log.i(TAG, "onCreate");

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GroceryAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        //Add Button
        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent showClass = new Intent(ItemList.this, NewItem.class);
                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(showClass);
                Log.i(TAG, "After startActivity");
            }
        });
    }

    public static void removeItem(long id){
        mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME,
                GroceryContract.GroceryEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());

    }

    public static Cursor getAllItems(){

        return mDatabase.query(
                GroceryContract.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

}
