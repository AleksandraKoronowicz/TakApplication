package com.application.tak.takapplication.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.application.tak.takapplication.actClientTask;
import com.application.tak.takapplication.actClientTaskNotSelected;

/**
 * Created by azielinska on 29.10.2017.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(context, actClientTask.class);
        context.startService(myIntent);

    }
}
