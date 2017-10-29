package com.application.tak.takapplication.services;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.RemoteViews;

import com.application.tak.takapplication.data_access.GetAllClientTasks;

import java.util.ArrayList;

public class BackgroundService extends Service {

    public static final String SERVICE_RESULT = "abc";
    LocalBroadcastManager broadcastMgr;
    NotificationManager mNotificationManager;
    GetAllClientTasks data;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        broadcastMgr = LocalBroadcastManager.getInstance(this);
    }

   /* public void sendResult() {



        data = new GetAllClientTasks(MainActivity.ctx);

        data.setDBRequestFinishedListener(new OnDBRequestFinished() {
            @Override
            public void onDBRequestFinished() {
                if(data._categories != null)
                {
                    Intent intent = new Intent(SERVICE_RESULT);
                    ArrayList<String> strings = new ArrayList<String>();
                    for(String s : data._categories)
                        strings.add(s);

                    intent.putStringArrayListExtra(SERVICE_RESULT,strings);
                    broadcastMgr.sendBroadcast(intent);
                }
            }
        });




    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Query the database and show alarm if it applies

        // Here you can return one of some different constants.
        // This one in particular means that if for some reason
        // this service is killed, we don't want to start it
        // again automatically

        //sendResult();
        //addNotification();

        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // I want to restart this service again in one hour
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (1000 * 15),
                PendingIntent.getService(this, 0, new Intent(this, BackgroundService.class), 0)
        );
    }


    private void addNotification() {

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       // PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

    /*    RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);
        //contentView.setImageViewResource(R.id.image, R.drawable.notify_icon);
        contentView.setTextViewText(R.id.title, "TAK");
        contentView.setTextViewText(R.id.text, "tu jest jakis tekst");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notify_icon)
                // .setContentTitle("TAK!")
                //.setContentText("Pojawiły się nowe zadania")
                .setContent(contentView)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .build();

        mNotificationManager.notify(0,notification);*/
    }
}
