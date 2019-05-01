package com.example.marcus.assignment5;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseScreen extends AppCompatActivity {
    static boolean mIsBound;
    static String TAG = "DatabaseScreen";
    static ArrayList<Film> films = new ArrayList<>();
    private FilmService mBoundService;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = ((FilmService.LocalBinder)service).getService();
            String currString = mBoundService.getCurrentString();

            // Tell the user about this for our demo.
            Toast.makeText(DatabaseScreen.this, R.string.local_service_connected,
                    Toast.LENGTH_SHORT).show();
        }

        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
            Toast.makeText(DatabaseScreen.this, R.string.local_service_disconnected,
                   Toast.LENGTH_SHORT).show();
        }
    };

    void doBindService() {
        Intent i = new Intent(DatabaseScreen.this, FilmService.class);
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    private AdapterView.OnItemClickListener mBindListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            doBindService();
        }
    };

    private AdapterView.OnItemClickListener mUnbindListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            doUnbindService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_screen);
        Toolbar toolbar = findViewById(R.id.screenMenu);
        setSupportActionBar(toolbar);
        Log.i(TAG, "onCreate");

        ListView listFilms = findViewById(R.id.listFilms);
        ArrayAdapter<Film> restaurantAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, films);
        listFilms.setAdapter(restaurantAdapter);

        //Watch for List Button Clicks
        if(mIsBound)
            listFilms.setOnItemClickListener(mUnbindListener);
        else
            listFilms.setOnItemClickListener(mBindListener);

        //Delete Film on hold
        listFilms.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DatabaseScreen.this, "Removed " + films.get(position),
                        Toast.LENGTH_SHORT).show();
                films.remove(position);
                finish();
                startActivity(getIntent());
                return true;
            }
        });

        //Add Button
        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addRestaurant = new Intent(DatabaseScreen.this, AddFilm.class);
                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(addRestaurant);
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
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, "onResume");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
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

            while(!films.isEmpty()){
                films.remove(0);
            }
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
                String date = "Demo";
                String file = "Demo";
                for (int i = 0; i < load.size(); i = i+3) {
                    name = load.get(i);
                    date = load.get(i+1);
                    file = load.get(i+2);
                    films.add(new Film(name, date, file));
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

        if(item.getItemId() == R.id.unbind) {

            doUnbindService();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
