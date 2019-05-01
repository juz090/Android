package specialActivity;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.chapman.dev.a4activities.R;
import edu.chapman.dev.a4activities.switchactivities.MyApplication;

public class ListActivityExample extends ListActivity {
    private static String TAG = "ListActivityExample";
    ArrayList<String> val;
    private ArrayAdapter<String> adapter;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG,"Create");
        val = new ArrayList<String>();
        val.add("Angels");
        val.add("BlueJays");
        val.add("Angels");
        val.add("BlueJays");
        val.add("Angels");
        val.add("BlueJays");
        val.add("Angels");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");



        MyApplication appState = (MyApplication) getApplication();
        appState.myList = val;



        adapter = new ArrayAdapter<String>(this,
                R.layout.row, R.id.label, appState.myList);
        setListAdapter(adapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
        MyApplication appState = (MyApplication) getApplication();
        appState.myList.remove(item);

        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"Destoryed");
        super.onDestroy();
    }
}
