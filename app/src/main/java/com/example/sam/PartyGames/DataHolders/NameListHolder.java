package com.example.sam.PartyGames.DataHolders;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Created by sam on 1/6/17.
 */

public class NameListHolder {
   static List<String> names;
    Context mContext;
    int pos =0;

    public NameListHolder(Context context) {
        mContext = context;
        names = new ArrayList();
        Map<String,?> keys = mContext.getSharedPreferences("Names", Context.MODE_PRIVATE).getAll();
        /*
        for(Map.Entry<String,?> entry : keys.entrySet()) {
            Log.d("map values", entry.getKey() + ": " +
                    entry.getValue().toString());
        }
        */
        Map<String, Object> map = new TreeMap<String, Object>(keys);
        for(Map.Entry<String,Object> entry: map.entrySet()){
           // Log.d("map values", entry.getKey() + ": " +entry.getValue().toString());
            names.add(entry.getValue().toString());
        }

    }
    public String getName(){
        String name="";
        if (pos >= names.size())
            pos = 0;
            else if (names.size() > 0) {
                name = names.get(pos);
                pos++;
            }
            return name;
    }
    static public List getNames(){
         List<String> temp = new ArrayList<>(names);
        return temp;
    }
    static public void replaceName(String name1,String name2){
        names.remove(name1);
        names.add(name1+" - "+name2);
    }
    static public void clear(){
        if(names != null)
            names.clear();
    }

}
