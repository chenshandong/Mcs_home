package com.txy.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.txy.activity.StartAppActivity;

/**
 * Created by Administrator on 2016/1/6.
 */
public class AutoStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, StartAppActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
