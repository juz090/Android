package edu.chapman.dev.a13db;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.widget.EditText;

public class TestContacts extends Activity {
    EditText et;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS},
                0);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_CONTACTS},
                0);


        et = new EditText(this);
        setContentView(et);

    }

    @Override
    protected void onResume() {

        super.onResume();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);
        String nameData = "";
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur
                        .getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur
                        .getString(cur
                                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                nameData += name + ":\n";

                System.out.println(" " + name);
            }
        }
        et.setText(nameData);
    }
}
