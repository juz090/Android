package com.example.user.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.login.R;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class LoginActivity extends Activity {
    private boolean mIsBound;
    EditText stock;


    private LocalService mBoundService;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            mBoundService = ((LocalService.LocalBinder)service).getService();
            String currString = mBoundService.getCurrentString();

            // Tell the user about this for our demo.
            Toast.makeText(LoginActivity.this, R.string.local_service_connected,
                    Toast.LENGTH_SHORT).show();
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            mBoundService = null;
            Toast.makeText(LoginActivity.this, R.string.local_service_disconnected,
                    Toast.LENGTH_SHORT).show();
        }
    };

    void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        Intent i = new Intent(LoginActivity.this, LocalService.class);
        i.putExtra("name", stock.getText().toString());
        i.putExtra("test", "Test");
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        String newStock = stock.getText().toString();
        if (newStock.equals("AMZN"))
            stock.setText("1755.25");
        else if (newStock.equals("AAPL"))
            stock.setText("216.36");
        else if (newStock.equals("BA"))
            stock.setText("367.47");

        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }


    private OnClickListener mBindListener = new OnClickListener() {
        public void onClick(View v) {
            doBindService();
        }
    };

    private static String TAG = "Binding";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate: "+ Thread.currentThread().getName());
        setContentView(R.layout.linear);

        stock = findViewById(R.id.name);

        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.btnEnter);
        button.setOnClickListener(mBindListener);

    }
}