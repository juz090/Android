package com.example.user.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.login.R;


public class LoginActivity extends Activity {
    // Initializing variables
    private ProgressBar bar;
    EditText inputName;
    EditText inputEmail;
    EditText loading;
    static String TAG = "FirstScreenActivity";

    private Handler handler = new Handler() {
        // Handle msg
        public void handleMessage(Message msg) {
            int val = msg.arg1;
            loading.setText(Integer.valueOf(val).toString());
        }
    };

    boolean isRunning = false;
    private Thread background;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);
        Log.i(TAG, "onCreate");


        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        Button btnNextScreen = (Button) findViewById(R.id.btnNextScreen);
        loading = findViewById(R.id.email);

        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

               //// loading.setText("Checking Password...");
               // isRunning = true;
              //  background.start();

                //Starting a new Intent
                Intent nextScreen = new Intent(LoginActivity.this, SecondActivity.class);

                //Sending data to another Activity
                nextScreen.putExtra("name", inputName.getText().toString());
                nextScreen.putExtra("email", inputEmail.getText().toString());

                loading.setText("Checking Password...");
                isRunning = true;
                background.start();

                Toast.makeText(LoginActivity.this, "Sign-In Successful", Toast.LENGTH_LONG).show();

                Log.i(TAG, "Before startActivity");
                // starting new activity
                startActivity(nextScreen);

                Log.i(TAG, "After startActivity");

            }
        });


    }

    public int cryptoCheck() {
        int x = 2;
        int small = 99;
        int large = 99999999;
        for (int i = 0; i < large; ++i) {
            x = x + 1;
            x = x - 1;
            x = x * 2;
            x = x / 2;
            x = x + 1;
            x = x - 1;
            x = x * 2;
            x = x / 2;
        }

        return x;
    }

    public void onStart() {
        super.onStart();

        // New Thread
        background = new Thread(new Runnable() {
            public void run() {
                // Sendmsg
                Message m = handler.obtainMessage();
                m.arg1 = cryptoCheck();
                handler.sendMessage(m);
                System.out.println("Send Msg:"
                        + Thread.currentThread().getName());

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

    public void onStop() {
        super.onStop();
        isRunning = false;
        inputEmail.findViewById(R.id.email);
    }

}