package com.apurba.infinityrecyclerview;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

public class DataItemDataSourceFactory extends DataSource.Factory<Integer, DataItem> {
    @NonNull
    @Override
    public DataSource<Integer, DataItem> create() {
        DataItemDataSource source = new DataItemDataSource();
        return source;
    }
}
