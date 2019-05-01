package com.example.user.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.login.R;


public class SecondActivity extends Activity {

    static String TAG = "SecondScreenActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Log.i(TAG, "onCreate");


        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
        Button btnSignOut = findViewById(R.id.btnSignOut);
        Button btnClose = (Button) findViewById(R.id.btnClose);

        Intent i = getIntent();
        // Receiving the Data
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");

        // Displaying Received data
        txtName.setText(name);
        txtEmail.setText(email);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater mInflater;
                mInflater = LayoutInflater.from(SecondActivity.this);
                //Inflate the view from xml
                final View newView = mInflater.inflate(R.layout.signout, null);
                Button btnYes = (Button) newView.findViewById(R.id.btnYes);
                Button btnNo = newView.findViewById(R.id.btnNo);

                final AlertDialog ad = new AlertDialog.Builder(SecondActivity.this)
                        .setTitle("Sign Out")
                        .setMessage("Are you sure you want to sign out?").setView(newView)
                        .show();

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent nextScreen = new Intent(SecondActivity.this, LoginActivity.class);

                        Log.i(TAG, "Before startActivity");
                        // starting new activity
                        startActivity(nextScreen);

                        Log.i(TAG, "After startActivity");
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        Log.i(TAG, "got clicked");
                        ad.dismiss();
                    }
                });
            }
        });

        // Binding Click event to Button
        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
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
