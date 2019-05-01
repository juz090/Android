package edu.chapman.dev.a13db;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ConstantsBrowser extends ListActivity {
    private static final int ADD_ID = Menu.FIRST + 1;
    private static final int DELETE_ID = Menu.FIRST + 3;
    private DatabaseHelper db = null;
    private Cursor constantsCursor = null;
    ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(this);
        constantsCursor = db.getReadableDatabase().rawQuery(
                "SELECT _ID, title, value " + "FROM constants ORDER BY title",
                null);

        adapter = new SimpleCursorAdapter(this, R.layout.row, constantsCursor, new String[] { DatabaseHelper.TITLE,
                DatabaseHelper.VALUE }, new int[] { R.id.title,
                R.id.value }, 0);


        setListAdapter(adapter);
        registerForContextMenu(getListView());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        constantsCursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, ADD_ID, Menu.NONE, "Add").setIcon(R.drawable.ic_launcher_background)
                .setAlphabeticShortcut('a');

        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ADD_ID:
                add();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, DELETE_ID, Menu.NONE, "Delete")
                .setAlphabeticShortcut('d');
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                        .getMenuInfo();

                delete(info.id);
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    private void add() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View addView = inflater.inflate(R.layout.add_edit, null);
        final DialogWrapper wrapper = new DialogWrapper(addView);

        new AlertDialog.Builder(this)
                .setTitle(R.string.add_title)
                .setView(addView)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                processAdd(wrapper);
                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // ignore, just dismiss
                            }
                        }).show();
    }

    private void delete(final long rowId) {
        if (rowId > 0) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.delete_title)
                    .setPositiveButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    processDelete(rowId);
                                }
                            })
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    // ignore, just dismiss
                                }
                            }).show();
        }
    }

    private void processAdd(DialogWrapper wrapper) {
        ContentValues values = new ContentValues(2);

        values.put(DatabaseHelper.TITLE, wrapper.getTitle());
        values.put(DatabaseHelper.VALUE, wrapper.getValue());

        long count = db.getWritableDatabase().insert("constants", DatabaseHelper.TITLE,
                values);

        Log.i("ConstantsBrowser", "Rows affected: " + count);
        ((SimpleCursorAdapter) getListAdapter()).swapCursor(constantsCursor);

    }

    private void processDelete(long rowId) {
        String[] args = { String.valueOf(rowId) };

        long count = db.getWritableDatabase().delete("constants", "_ID=?", args);

        Log.i("ConstantsBrowser", "Rows affected: " + count);

        constantsCursor = db.getReadableDatabase().rawQuery(
                "SELECT _ID, title, value " + "FROM constants ORDER BY title",
                null);

        ((SimpleCursorAdapter) getListAdapter()).swapCursor(constantsCursor);


    }

    class DialogWrapper {
        EditText titleField = null;
        EditText valueField = null;
        View base = null;

        DialogWrapper(View base) {
            this.base = base;
            valueField = (EditText) base.findViewById(R.id.value);
        }

        String getTitle() {
            return (getTitleField().getText().toString());
        }

        float getValue() {
            return (new Float(getValueField().getText().toString())
                    .floatValue());
        }

        private EditText getTitleField() {
            if (titleField == null) {
                titleField = (EditText) base.findViewById(R.id.title);
            }

            return (titleField);
        }

        private EditText getValueField() {
            if (valueField == null) {
                valueField = (EditText) base.findViewById(R.id.value);
            }

            return (valueField);
        }
    }
}