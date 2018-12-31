package com.android.example.slicecodelab;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;

import java.util.Objects;

import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;

public class MySliceProvider extends SliceProvider {

    private static int sReqCode = 0;

    @Override
    public boolean onCreateSliceProvider() {
        return true;
    }

    @Override
    public Slice onBindSlice(Uri sliceUri) {
        switch(Objects.requireNonNull(sliceUri.getPath())) {
            case "/temperature":
                return createTemperatureSlice(sliceUri);
            default:
                return null;
        }
    }

    private Slice createTemperatureSlice(Uri sliceUri) {
        final SliceAction tempUp = SliceAction.create(getChangeTempIntent(MainActivity.sTemperature + 1),
                IconCompat.createWithResource(getContext(), R.drawable.ic_temp_up),
                ListBuilder.ICON_IMAGE, "Increase temperature");

        final SliceAction tempDown = SliceAction.create(getChangeTempIntent(MainActivity.sTemperature - 1),
                IconCompat.createWithResource(getContext(), R.drawable.ic_temp_down),
                ListBuilder.ICON_IMAGE, "Decrease temperature");

        final Intent intent = new Intent(getContext(), MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
        final SliceAction openTempActivity = SliceAction.createDeeplink(pendingIntent,
                IconCompat.createWithResource(getContext(), R.drawable.ic_launcher_foreground),
                ListBuilder.UNKNOWN_IMAGE, "Temperature controls");

        final ListBuilder.RowBuilder temperatureRow = new ListBuilder.RowBuilder()
                .setTitle(MainActivity.getTemperatureString(getContext()))
                .addEndItem(tempUp)
                .addEndItem(tempDown)
                .setPrimaryAction(openTempActivity);
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .addRow(temperatureRow)
                .build();
    }

    private PendingIntent getChangeTempIntent(int value) {
        final Intent intent = new Intent(MyBroadcastReceiver.ACTION_CHANGE_TEMP);
        intent.setClass(getContext(), MyBroadcastReceiver.class);
        intent.putExtra(MyBroadcastReceiver.EXTRA_TEMP_VALUE, value);
        return PendingIntent.getBroadcast(getContext(), sReqCode++, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
