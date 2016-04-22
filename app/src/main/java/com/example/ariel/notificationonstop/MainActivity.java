package com.example.ariel.notificationonstop;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private final int requestCode = 10;
    private final int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBuilder =
                        new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Title")
                        .setContentText("Notification content text...");
// intent for notification to get back to activity as it is. all these flags needed + manifest android:launchMode="singleInstance"
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);



        PendingIntent notificationPendingIntent =
                PendingIntent.getActivity(this, requestCode,
                        notificationIntent, 0);
        mBuilder.setContentIntent(notificationPendingIntent);
        mBuilder.setAutoCancel(true);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, mBuilder.build());
    }

}
