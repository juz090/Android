package com.example.marcus.assignment5;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Controller extends Activity {
    private static String TAG = "Controller";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.controller);

        // Watch for button clicks.
       // Button button = (Button)findViewById(R.id.start);
        //button.setOnClickListener(mStartListener);
    }

    private OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            startService(new Intent(Controller.this,
                    FilmService.class));
        }
    };

}