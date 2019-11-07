package com.apurba.infinityrecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "MainActivity";

    private EndlessRecyclerViewScrollListener scrollListener;
    RecyclerView recyclerView;
    DataItemAdapter adapter;

    LiveData<PagedList<DataItem>> dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
    }

    private void setRecyclerView(){
        recyclerView = findViewById(R.id.rv_infinity);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new DataItemAdapter();


        // Initial page size to fetch can also be configured here too
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(20).build();

        DataItemDataSourceFactory factory = new DataItemDataSourceFactory();
        dataItems = new LivePagedListBuilder(factory, config).build();

        dataItems.observe(this, new Observer<PagedList<DataItem>>() {

            @Override
            public void onChanged(PagedList<DataItem> dataItems) {
                adapter.submitList(dataItems);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /*
    private void loadMore(int page,int totalItem, int dx, int dy){
        Log.d(LOG_TAG, "page : " + page + "Total Items : " + totalItem + " dx : " + dx + " dy : " + dy);
        List<DataItem> dataSet = new ArrayList<>();
        if (dy>0){
            dataSet = VirtualRESTAPi.getNextData(totalItem, true);
        }
        if (dy<0){
            dataSet = VirtualRESTAPi.getNextData(totalItem, false);
        }

        final int size = dataSet.size();

        adapter.addAll(dataSet);
        recyclerView.post(new Runnable() {
            public void run() {
                // There is no need to use notifyDataSetChanged()
                adapter.notifyItemInserted(size - 1);
            }
        });
    }

     */
}
