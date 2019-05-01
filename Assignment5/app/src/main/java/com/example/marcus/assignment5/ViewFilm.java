package com.example.marcus.assignment5;

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

public class ViewFilm extends Activity {

    static String TAG = "ViewRestaurant";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewfilm);
        Log.i(TAG, "onCreate");

        TextView txtName = findViewById(R.id.name);
        TextView txtDate = findViewById(R.id.date);
        TextView txtFile = findViewById(R.id.file);

        Intent i = getIntent();
        // Receiving the Data
        final int index = i.getIntExtra("num", 0);
        final String name = DatabaseScreen.films.get(index).getName();
        final String date = DatabaseScreen.films.get(index).getDate();
        final String file = DatabaseScreen.films.get(index).getFile();

        // Displaying Received data
        txtName.setText(name);
        txtDate.setText(date);
        txtFile.setText(file);

        // Update Button
        //btnStop.setOnClickListener(mStopListener);


    }

    private View.OnClickListener mStartListener = new View.OnClickListener() {
        public void onClick(View v) {
            // Make sure the service is started.  It will continue running
            // until someone calls stopService().  The Intent we use to find
            // the service explicitly specifies our service component, because
            // we want it running in our own process and don't want other
            // applications to replace it.
            startService(new Intent(ViewFilm.this,
                    FilmService.class));
        }
    };

    private View.OnClickListener mStopListener = new View.OnClickListener() {
        public void onClick(View v) {
            // Cancel a previous call to startService().  Note that the
            // service will not actually stop at this point if there are
            // still bound clients.
            stopService(new Intent(ViewFilm.this,
                    FilmService.class));
        }
    };

   // @Override
   // protected void onPause() {
        // TODO Auto-generated method stub
   //     super.onPause();
    //    Log.i(TAG, "onPause");
   // }

   // @Override
  //  protected void onDestroy() {
   //     // TODO Auto-generated method stub
    //    super.onDestroy();
    //    Log.i(TAG, "onDestroy");

   // }
}


