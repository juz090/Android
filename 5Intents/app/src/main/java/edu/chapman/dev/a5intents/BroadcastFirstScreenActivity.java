package edu.chapman.dev.a5intents;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class BroadcastFirstScreenActivity extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputEmail;
    static String TAG = "FirstScreenActivity";
    int REQUEST_CODE = 317;

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
                Intent i = new Intent();
                i.setAction("Hi");
                i.putExtra("name", "test12341");
                BroadcastFirstScreenActivity.this.sendBroadcast(i);
                System.out.println(Thread.currentThread().getName());

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