package edu.chapman.dev.a12files;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadWriteFileDemo extends Activity {
    private final static String SaveFile = "myfile.dat";
    private EditText editor;
    private String TAG;
    private MyData[] md;
    private FileOutputStream myFile;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.main);
        editor = (EditText) findViewById(R.id.editor);

    }

    @Override
    protected void onResume() {

        super.onResume();

        try {
            myFile = this.openFileOutput(SaveFile, MODE_APPEND);
            File f = getFileStreamPath(SaveFile);
            // Read if exists
            if (f.length() > 1) {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fis);
                md = (MyData[]) in.readObject();
                editor.setText(md[1].getName());
            // Write if not
            } else {
                md = new MyData[2];
                md[0] = new MyData(5, "Stuff0", 1.0);
                md[1] = new MyData(5, "Stuff1", 1.0);

                ObjectOutputStream out = new ObjectOutputStream(myFile);
                out.writeObject(md);
                out.close();
                editor.setText("Wrote out object");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}