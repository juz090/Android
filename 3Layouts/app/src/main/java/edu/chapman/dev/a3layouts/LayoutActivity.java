package edu.chapman.dev.a3layouts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LayoutActivity extends Activity {

    Button btn;
    EditText edtText;
    CheckBox chkBox;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //setContentView(R.layout.main);  //Test Different layouts (Table layout)
        //setContentView(R.layout.linear);
        //setContentView(R.layout.frame);
        setContentView(R.layout.relative);
        //setContentView(R.layout.scroll);
        //and more


    }
}
