package com.gery.collection.controller.map;

import com.alibaba.fastjson.JSON;
import com.gery.collection.model.User;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;

import java.util.Collection;
import java.util.Map;

/**
 * @program: gery-demo-2023
 * @ClassName DiffMapController
 * @description:
 * @author: yaowenhua
 * @create: 2023-09-01 10:07
 * @Version 1.0
 **/
public class DiffMapController {
    public static void main(String[] args) {

      //  onlyMap();
        SetMultimap<String,String> setMultimap= HashMultimap.create();
        setMultimap.put("key1","value1");
        setMultimap.put("key1","value2");
        setMultimap.put("key1","value3");

   SetMultimap<String,String> setMultimap2= HashMultimap.create();
        setMultimap2.put("key1","value1");
        setMultimap2.put("key1","value2");
        MapDifference<String, Collection<String>> difference = Maps.difference(setMultimap.asMap(), setMultimap2.asMap());
        Map<String, MapDifference.ValueDifference<Collection<String>>> stringValueDifferenceMap = difference.entriesDiffering();
        System.out.println(stringValueDifferenceMap);



    }

    private static void onlyMap() {
        Map<String, User> map3 = Maps.newHashMap();
        map3.put("key",new User("1", "1", 1, 1, null));

        Map<String, User> map4 = Maps.newHashMap();
        map4.put("key",new User("1", "1", 1, 1, null));
        MapDifference<String, User> difference = Maps.difference(map3, map4);
        Map<String, MapDifference.ValueDifference<User>> stringValueDifferenceMap = difference.entriesDiffering();
        Map<String, User> map = difference.entriesInCommon();

        System.out.println(JSON.toJSON(stringValueDifferenceMap));
        System.out.println(JSON.toJSON(map));

//        Map<String, String> map1 = Maps.newHashMap();
//        map1.put("key1", "value1");
//        map1.put("key2", "value2");
//        map1.put("key3", "value3");
//
//        Map<String, String> map2 = Maps.newHashMap();
//        map2.put("key1", "value1");
//        map2.put("key2", "new_value"); // 不同的值
//        map2.put("key4", "value4");    // map2 中有而 map1 中没有的键
//
//        MapDifference<String, String> difference = Maps.difference(map1, map2);
//
//        System.out.println("Entries in common: " + difference.entriesInCommon());
//        System.out.println("Entries differing: " + difference.entriesDiffering());
//        System.out.println("Entries only in left (map1): " + difference.entriesOnlyOnLeft());
//        System.out.println("Entries only in right (map2): " + difference.entriesOnlyOnRight());
//
//        // 输出值差异
//        Map<String, MapDifference.ValueDifference<String>> stringValueDifferenceMap = difference.entriesDiffering();
//        for (Map.Entry<String, MapDifference.ValueDifference<String>> entry : stringValueDifferenceMap.entrySet()) {
//            String entryKey = entry.getKey();
//            MapDifference.ValueDifference<String> value = entry.getValue();
//            String s = value.leftValue();
//            String s1 = value.rightValue();
//            System.out.println(entryKey);
//            System.out.println(s);
//            System.out.println(s1);
//        }
    }

}
