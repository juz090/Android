package edu.chapman.dev.a8prefs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SimplePrefsDemo extends Activity {
    private static final int EDIT_ID = Menu.FIRST + 2;

    private TextView checkbox = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        checkbox = (TextView) findViewById(R.id.checkbox);
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        checkbox.setText(new Boolean(prefs.getBoolean("checkbox", false))
                .toString());
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, EDIT_ID, Menu.NONE, "Edit Prefs")
                .setIcon(R.drawable.ic_launcher_background).setAlphabeticShortcut('e');

        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case EDIT_ID:
                startActivity(new Intent(this, EditPreferences.class));
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }
}
