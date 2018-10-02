package com.example.vlad.organiserapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    int eventId;
    @Override
    public void onReceive(Context context, Intent intent) {

        // get eventByIs
        eventId = intent.getExtras().getInt("eventId");

        Intent showEventNotificationIntent = new Intent(context,ShowEventNotificationActivity.class);
        showEventNotificationIntent.putExtra("eventId",eventId);
        showEventNotificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,RequestCodes.NOTIFICATION_REQUEST_CODE,showEventNotificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Simple Notification")
                .setContentText("Take a picture and after that press notification button !")
                .setAutoCancel(true);

        notificationManager.notify(RequestCodes.NOTIFICATION_REQUEST_CODE, mBuilder.build());

    }
}
