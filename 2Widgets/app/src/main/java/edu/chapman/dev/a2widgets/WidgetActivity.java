package edu.chapman.dev.a2widgets;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WidgetActivity extends Activity {


    private Button btn;
    private EditText edtText; // These are used fundamentally the same way
    private CheckBox chkBox;
    private static String TAG = "Widget2Activity"; // TAG is used for logging

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()"); // Logging at debug level, see output in logcat

        btn = new Button(this);
        btn.setText("My Button");



        //setContentView(btn);  // Add one view


        // Add additional widgets
        LinearLayout ll = new LinearLayout(this);
        EditText et = new EditText(this);
        CheckBox cb = new CheckBox(this);

        et.setText("Text in a textbox");
        cb.setChecked(true);

        ll.addView(btn);
        ll.addView(et);
        ll.addView(cb);

        setContentView(ll); // Add a layout containing views

        btn.setOnClickListener(new View.OnClickListener() { // registering a click listener

            public void onClick(View v) {
                Log.d(TAG, "Button clicked");


            }
        });


    }
}