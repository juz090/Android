package com.example.user.login;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;




public class LocalService extends Service {
    private NotificationManager mNM;
    private String TAG = "LocalService";

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate: "+ Thread.currentThread().getName());
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // Display a notification about us starting.  We put an icon in the status bar.
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy()");
        // Cancel the persistent notification.
        mNM.cancel(R.string.local_service_started);

        // Tell the user we stopped.
        Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotificationManager.cancel(0);
    }
    String s = "";
    @Override
    public IBinder onBind(Intent intent) {
        s = intent.getStringExtra("test");
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.local_service_started);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(LocalService.this, "Chapman")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Service") // title for notification
                .setContentText(text)// message for notification
                .setAutoCancel(true); // clear notification after click

        Intent intent = new Intent(LocalService.this, Controller.class);
        PendingIntent pi = PendingIntent.getActivity(LocalService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());


    }

    public String getCurrentString(){

        return s + "service";
    }

}
