package com.assignment.log_parser;

import java.util.*;

public class LRUCache {

    private long cacheSize;
    private HashMap<Integer,Val> keyValMap;
    private SortedMap<Long,Integer> sortedMap;

    public LRUCache(long cacheSize) {
        this.cacheSize = cacheSize;
        this.keyValMap = new HashMap<>();
        this.sortedMap = new TreeMap<>();
    }

    public void insert(Integer key, Integer val) {

        if(!keyValMap.containsKey(key)) {
            if (keyValMap.size() < cacheSize) {
                long ts = System.currentTimeMillis();
                ts  = ts + keyValMap.size();
                sortedMap.put(ts,key);
                keyValMap.put(key, new Val(val, ts));
            } else {
                //remove the earliest put key val pair
                long ts = System.currentTimeMillis();
                ts  = ts + keyValMap.size();
//                Optional<Map.Entry<Integer, Val>> first = keyValMap.entrySet().stream().min(Comparator.comparing(k -> k.getValue().getTs()));
//                if(first.isPresent()) {
//                    keyValMap.remove(first.get().getKey());
//                    keyValMap.put(key, new Val(val, ts));
//                }

                Integer first = sortedMap.get(sortedMap.firstKey());
                sortedMap.remove(sortedMap.firstKey());
                sortedMap.put(ts,key);

                keyValMap.remove(first);
                keyValMap.put(key, new Val(val, ts));
            }
        }
        else {
            long ts = System.currentTimeMillis();
            ts  = ts + keyValMap.size();
            sortedMap.put(ts,key);
            keyValMap.put(key,new Val(val, ts));
        }
    }

    public Integer getByKey(Integer key) {
        if(keyValMap.containsKey(key)) {
            sortedMap.remove(keyValMap.get(key).getTs());
            sortedMap.put(System.currentTimeMillis(),key);
            return keyValMap.get(key).getVal();
        }
        return null;
    }

    public void show() {
        System.out.println("\n");
        keyValMap
                .forEach((k,v)->{
            System.out.println(k+" : " +v.getVal() + " : " + v.getTs());
        });
    }

}
