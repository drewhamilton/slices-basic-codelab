package com.android.example.slicecodelab;

import android.net.Uri;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.ListBuilder;

public class MySliceProvider extends SliceProvider {

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
        ListBuilder listBuilder = new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY);

        ListBuilder.RowBuilder temperatureRow = new ListBuilder.RowBuilder(listBuilder)
                .setTitle(MainActivity.getTemperatureString(getContext()));

        // TODO: add actions to row; in later step

        listBuilder.addRow(temperatureRow);

        return listBuilder.build();
    }
}
