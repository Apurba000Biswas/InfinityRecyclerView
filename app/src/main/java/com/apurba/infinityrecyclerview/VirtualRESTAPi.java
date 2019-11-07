package com.apurba.infinityrecyclerview;

import java.util.ArrayList;
import java.util.List;

public class VirtualRESTAPi {

    private static final int MAX_THRESHOLD = 20;

    private static List<DataItem> get200Data(){
        List<DataItem> dataItems = new ArrayList<>();
        for (int i=0; i<200; i++){
            DataItem data = new DataItem("Apurba", i);
            dataItems.add(data);
        }
        return dataItems;
    }

    public static List<DataItem> getNextData(int id){
        List<DataItem> database = get200Data();
        if (id >= database.size()-1) return null;
        List<DataItem> pageData = new ArrayList<>();
        int count = 0;
        while (id < database.size() && count < MAX_THRESHOLD){
            pageData.add(database.get(id));
            id++;
            count ++;
        }

        return pageData;
    }
}
