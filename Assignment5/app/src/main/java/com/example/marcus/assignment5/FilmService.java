package com.example.marcus.assignment5;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;




public class FilmService extends Service {
    private NotificationManager mNM;
    private String TAG = "FilmService";

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        FilmService getService() {
            return FilmService.this;
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
    int index;
    String s = "";
    @Override
    public IBinder onBind(Intent intent) {
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
        NotificationChannel mChannel= new NotificationChannel("Chapman", "name", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);

        String name = DatabaseScreen.films.get(index).getName();

        Intent intent = new Intent(FilmService.this, ViewFilm.class);
        PendingIntent pi = PendingIntent.getActivity(FilmService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(FilmService.this, "Chapman")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(name + "is Playing") // title for notification
                .setContentText(text)// message for notification
                .setAutoCancel(true) // clear notification after click
                .setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());


    }

    public String getCurrentString(){

        return s + "service";
    }

}

