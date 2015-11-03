package com.remed1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by ΜΗΤΣΟΣ on 13/10/2015.
 */
public class AlertMsg extends BroadcastReceiver {

    DatabaseHelper myDb;
    int icon = R.mipmap.ic_action;
    long when = System.currentTimeMillis();
    //public static int i =0;
    private static final String tag = "ALongRunningReceiver";

    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.d(tag, "----------------------------------Receiver started");

        createNot(context, intent.getExtras().getInt("id"), intent);

        Log.d(tag, "-----------------------------Receiver finished");
    }



    public void createNot(Context context, int id, Intent info) {



        DateFormat df = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");


        String time = df.format(when);
        String mpid = String.valueOf(id);

      Intent intent = new Intent(context, NotificationInfos.class);
      intent.putExtra("Number_of_intent", id);
      intent.putExtra("name",info.getExtras().getString("name"));
        intent.putExtra("missedid", mpid);
        intent.putExtra("time",  time);

        Intent intent1 = new Intent(context, MissedNotifications.class);
        intent1.putExtra("Number_of_intent",id);
        intent1.putExtra("missedid", mpid);
        intent1.putExtra("name", info.getExtras().getString("name"));
        intent1.putExtra("time",  time);

        PendingIntent contentIntent = PendingIntent.getActivity(context,id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent contentIntent1 = PendingIntent.getActivity(context,id, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new  NotificationCompat.Builder(context)
                .setSmallIcon(icon)
                .setTicker("Remember to  take your pill  " + info.getExtras().getString("name"))
                .setWhen(when)
                .setContentTitle("PILL REMINDER !!!")
                .addAction(R.mipmap.ic_accept, "OK", contentIntent)
                .addAction(R.mipmap.ic_cancel, "Cancel", contentIntent1)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Don't forget to take your pill with \n" +
                                "Name: " + info.getExtras().getString("name") + "\n" +
                                "Dosage: " + info.getExtras().getString("dosage") + "\n" +
                                "Instructions: " + info.getExtras().getString("instructions")));




        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);

        builder.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



        notificationManager.notify(id,builder.build());

    }


}
