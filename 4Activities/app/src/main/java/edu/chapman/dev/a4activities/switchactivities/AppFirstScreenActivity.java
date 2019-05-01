package edu.chapman.dev.a4activities.switchactivities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.chapman.dev.a4activities.R;

public class AppFirstScreenActivity extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputEmail;
    static String TAG = "AppFirstScreenActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        Log.i(TAG, "onCreate");


        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        Button btnNextScreen = (Button) findViewById(R.id.btnNextScreen);

        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), AppSecondScreenActivity.class);

                //Sending data to another Activity
                //nextScreen.putExtra("name", inputName.getText().toString());
                //nextScreen.putExtra("email", inputEmail.getText().toString());

                //Put data into global
                MyApplication appState = (MyApplication)getApplication();
                appState.setInputName(inputName.getText().toString());
                appState.setInputEmail(inputEmail.getText().toString());


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

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, "onResume");

    }

}