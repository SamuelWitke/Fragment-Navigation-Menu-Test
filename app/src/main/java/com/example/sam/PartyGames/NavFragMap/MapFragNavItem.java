package com.example.sam.PartyGames.NavFragMap;


import android.support.v4.app.Fragment;

import com.example.sam.PartyGames.models.NavItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sam on 12/28/16.
 */

public class MapFragNavItem {
    private Map<String, PairKey<NavItem,Fragment>> map;
    public MapFragNavItem() {
        map = new HashMap<String, PairKey<NavItem, Fragment>>();
    }
    public void add(String key,NavItem val1, Fragment val2) {
        map.put(key,PairKey.make(val1,val2));
    }
    public NavItem getFirst(String key) {
        return map.get(key).getFirst();
    }
    public Fragment getSecond(String key) {
        return map.get(key).getSecond();
    }
}
