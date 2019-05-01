package edu.chapman.dev.a9resource;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class RawResource extends Activity {
    private static String LOG_APP_TAG = "RawResource";

    private EditText editField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rawlayout);
        editField = (EditText) findViewById(R.id.textId);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                InputStream inputStream = null;
                try {
                    inputStream = getResources().openRawResource(R.raw.hello);
                    byte[] reader = new byte[inputStream.available()];
                    while (inputStream.read(reader) != -1) {}
                    editField.setText(new String(reader));
                    editField.setSelection(editField.getText().length());
                } catch(IOException e) {
                    Log.e(LOG_APP_TAG, e.getMessage());
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            Log.e(LOG_APP_TAG, e.getMessage());
                        }
                    }
                }
            }
        });
    }
}