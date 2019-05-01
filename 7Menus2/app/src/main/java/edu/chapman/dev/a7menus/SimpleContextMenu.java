package edu.chapman.dev.a7menus;


//ContextMenu Example using Layout
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;

public class SimpleContextMenu extends Activity {
    private Button btn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn = (Button) findViewById(R.id.myBtn);

         //Register context menu for button
        registerForContextMenu(btn);

        //Long click listener
        btn.setOnLongClickListener(new OnLongClickListener() {

            public boolean onLongClick(View v) {
                System.out.println("onLongClick");
                return false; // consumed vs. non-consumed
            }
        });

        btn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                System.out.println("onClick");
            }
        });
    }
    //Callback for when long click/invoking menu
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        System.out.println("Menu!");

        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
    }
    //Callback for menu options click
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Action 1") {
            System.out.println("item1");
        } else if (item.getTitle() == "Action 2") {
            System.out.println("item2");
        } else {
            return false;
        }
        return true;
    }

}
