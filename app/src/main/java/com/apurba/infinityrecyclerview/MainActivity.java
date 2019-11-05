package com.apurba.infinityrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "MainActivity";

    private EndlessRecyclerViewScrollListener scrollListener;
    RecyclerView recyclerView;
    DataItemAdapter adapter;

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

        List<DataItem> dataSet = VirtualRESTAPi.getNextData(0, true);
        adapter = new DataItemAdapter(dataSet);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, int dx, int dy) {
                loadMore(page,totalItemsCount, dx, dy);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

        recyclerView.setAdapter(adapter);
    }

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
}
