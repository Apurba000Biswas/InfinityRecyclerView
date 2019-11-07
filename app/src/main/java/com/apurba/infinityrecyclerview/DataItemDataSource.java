package com.apurba.infinityrecyclerview;

import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

public class DataItemDataSource extends ItemKeyedDataSource<Integer, DataItem> {

    private static String LOG_TAG = "DataItemDataSource";

    DataItemDataSource(){
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<DataItem> callback) {
        callback.onResult(VirtualRESTAPi.getNextData(0));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataItem> callback) {
        List<DataItem> newData = VirtualRESTAPi.getNextData(params.key);
        Log.d(LOG_TAG, "****  Key = " + params.key);
        if (newData == null){
            Log.d(LOG_TAG, "**** Here is the boundary for Key = " + params.key);
            return;
        }
        callback.onResult(newData);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataItem> callback) {

    }


    @NonNull
    @Override
    public Integer getKey(@NonNull DataItem item) {
        return item.getId();
    }
}
