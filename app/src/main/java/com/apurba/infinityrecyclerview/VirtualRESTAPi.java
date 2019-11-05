package com.apurba.infinityrecyclerview;

import java.util.ArrayList;
import java.util.List;

public class VirtualRESTAPi {

    private static final int MAX_THRESHOLD = 20;

    private static List<DataItem> get4000Data(){
        List<DataItem> dataItems = new ArrayList<>();
        for (int i=0; i<4000; i++){
            DataItem data = new DataItem("Apurba", i+"");
            dataItems.add(data);
        }
        return dataItems;
    }

    public static List<DataItem> getNextData(int page, boolean isDown){
        List<DataItem> database = get4000Data();
        List<DataItem> pageData = new ArrayList<>();
        int index = page;
        int count = 0;
        if (isDown){
            while (index < database.size() && count < MAX_THRESHOLD){
                pageData.add(database.get(index));
                index++;
                count++;
            }
        }else {
            while (index > 0 && count < MAX_THRESHOLD){
                pageData.add(database.get(index));
                index--;
                count++;
            }
        }

        return pageData;
    }
}
