package com.example.sam.PartyGames.DataHolders;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by sam on 12/29/16.
 */
public class DataHolder {
    static Map<String, WeakReference<Boolean>> data;

    public static void save(String id, Boolean object) {
        if(data != null)
            data.put(id, new WeakReference<Boolean>(object));
        else
            data = new HashMap<String, WeakReference<Boolean>>();
        }

    public static Boolean retrieve(String id) {
        WeakReference<Boolean> objectWeakReference;
        try{
            objectWeakReference = data.get(id);
            return objectWeakReference.get();
        }catch (Exception e){
            data = new HashMap<String, WeakReference<Boolean>>();
            return false;
        }
    }
    public static void Clear() {
        data.clear();
    }
}