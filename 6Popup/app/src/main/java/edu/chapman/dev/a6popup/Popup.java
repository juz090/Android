package edu.chapman.dev.a6popup;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Popup extends Activity {
    private static String TAG = "Popup Activity";
    private Button toastBtn;
    private Button dialogBtn;
    private Button dialogBtn2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toastBtn = (Button) findViewById(R.id.toastBtn);
        toastBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(Popup.this, "Toast Message", Toast.LENGTH_LONG)
                        .show(); // toast here
            }
        });
        dialogBtn = (Button) findViewById(R.id.dialogBtn); // dialog box here

        dialogBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                LayoutInflater mInflater;
                mInflater = LayoutInflater.from(Popup.this);
                //Inflate the view from xml
                final View newView = mInflater.inflate(R.layout.dialog, null);
                Button b = (Button) newView.findViewById(R.id.clickBtn);

                final AlertDialog ad = new AlertDialog.Builder(Popup.this)
                        .setTitle("DialogBox")
                        .setMessage("Message of box goes here").setView(newView)
                        .show();


                b.setOnClickListener(new OnClickListener() {

                    public void onClick(View arg0) {
                        Log.i(TAG, "got clicked");
                        ad.dismiss();
                    }
                });




            }
        });

        dialogBtn2 = (Button) findViewById(R.id.dialogBtn2); // dialog box here

        dialogBtn2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlertDialog ad = new AlertDialog.Builder(Popup.this)
                        .setTitle("DialogBox").setMessage("This is the msg!")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("OK", null).show();
            }

        });
    }

}