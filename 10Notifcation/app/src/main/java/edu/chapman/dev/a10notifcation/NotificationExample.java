package edu.chapman.dev.a10notifcation;


import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationExample extends Activity {

    private Button btnStatus;
    private int nID = 0;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnStatus = (Button) findViewById(R.id.statusBtn);



        btnStatus.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {


                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

 /*                   if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("chapman",
                                "Chapman_channel",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        channel.setDescription("Channel description");
                        mNotificationManager.createNotificationChannel(channel);
                    }
 */

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationExample.this, "default")
                            .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                            .setContentTitle("Notifcation title") // title for notification
                            .setContentText("Notification description")// message for notification
                            .setAutoCancel(true); // clear notification after click

                    Intent intent = new Intent(NotificationExample.this, NotificationExample.class);
                    PendingIntent pi = PendingIntent.getActivity(NotificationExample.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pi);
                    mNotificationManager.notify(nID, mBuilder.build());


            }
        });


    }



}///
