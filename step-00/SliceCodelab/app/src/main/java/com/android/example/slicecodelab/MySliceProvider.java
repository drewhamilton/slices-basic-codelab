package com.android.example.slicecodelab;

import android.net.Uri;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;

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

    @Nullable
    private Slice createTemperatureSlice(Uri sliceUri) {
        // TODO
        return null;
    }
}
