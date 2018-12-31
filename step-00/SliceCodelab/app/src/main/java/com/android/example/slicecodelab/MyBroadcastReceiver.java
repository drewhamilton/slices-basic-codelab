package com.android.example.slicecodelab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Backbase R&D B.V. on 31/12/2018.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_CHANGE_TEMP = "com.android.example.slicecodelab.ACTION_CHANGE_TEMP";
    public static final String EXTRA_TEMP_VALUE = "com.android.example.slicecodelab.EXTRA_TEMP_VALUE";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (ACTION_CHANGE_TEMP.equals(action) && intent.getExtras() != null) {
            int newValue = intent.getExtras().getInt(EXTRA_TEMP_VALUE, MainActivity.sTemperature);
            MainActivity.updateTemperature(context, newValue);
        }
    }
}
