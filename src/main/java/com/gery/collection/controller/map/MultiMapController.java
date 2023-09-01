package com.gery.collection.controller.map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.TreeMultimap;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: gery-demo-2023
 * @ClassName MultiMapController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-15 11:37
 * @Version 1.0
 **/
public class MultiMapController {
    public static void main(String[] args) {

        listMap();
        //  setMap();
//        SetMultimap<String, String> setMultimap = HashMultimap.create();
//        Set<String> strings = setMultimap.get("2");
//        System.out.println(strings);
//        setMultimap.put("2","3");
//        boolean b = setMultimap.containsValue("3");
//
//
//        MultiValuedMap<String, String> multiValuedMap = new ArrayListValuedHashMap<>();
//        Collection<String> strings1 = multiValuedMap.get("2");
//        System.out.println(strings1);

    }

    private static void setMap() {
        //适用于不需要保留插入顺序且不允许重复值的场景。
        SetMultimap<String, String> setMultimap = HashMultimap.create();
        setMultimap.put("3", "4");
        setMultimap.put("3", "3");
        setMultimap.put("2", "4");
        setMultimap.put("4", "4");
        setMultimap.put("2", "3");
        setMultimap.put("1", "2");
        setMultimap.put("1", "4");
        setMultimap.put("1", "3");


        System.out.println(setMultimap.keySet());

        Set<String> stringSet = setMultimap.get("1");
        System.out.println(stringSet);

        setMultimap.keySet().forEach(key -> {
            Set<String> values = setMultimap.get(key);
            System.out.println("Key: " + key + ", Values: " + values);
        });
        System.out.println("setMultimap------------------------------------------------------");

        //适用于保留插入顺序且不允许重复值的场景。
        SetMultimap<String, String> linkedHashMultimap = LinkedHashMultimap.create();
        linkedHashMultimap.put("3", "1");
        linkedHashMultimap.put("3", "5");
        linkedHashMultimap.put("3", "4");
        linkedHashMultimap.put("2", "4");
        linkedHashMultimap.put("4", "4");
        linkedHashMultimap.put("2", "3");
        linkedHashMultimap.put("1", "2");
        linkedHashMultimap.put("1", "4");
        linkedHashMultimap.put("1", "3");

        System.out.println(linkedHashMultimap.keySet());
        Set<String> set = linkedHashMultimap.get("1");
        System.out.println(set);

        linkedHashMultimap.keySet().forEach(key -> {
            Set<String> values = linkedHashMultimap.get(key);
            System.out.println("Key: " + key + ", Values: " + values);
        });
        System.out.println("linkedHashMultimap------------------------------------------------------");

        SetMultimap<String, String> treeMultimap = TreeMultimap.create();
        treeMultimap.put("3", "4");
        treeMultimap.put("3", "3");
        treeMultimap.put("2", "4");
        treeMultimap.put("4", "4");
        treeMultimap.put("2", "3");
        treeMultimap.put("1", "2");
        treeMultimap.put("1", "4");
        treeMultimap.put("1", "3");
        treeMultimap.put("1", "3");

        Set<String> strings = treeMultimap.get("1");

        System.out.println(strings);

        treeMultimap.keySet().forEach(key -> {
            Set<String> values = treeMultimap.get(key);
            System.out.println("Key: " + key + ", Values: " + values);
        });
    }

    private static void listMap() {
        //适用于需要保留插入顺序并允许重复值的场景
        ListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("3", "4");
        multimap.put("2", "4");
        multimap.put("4", "4");
        multimap.put("2", "3");
        multimap.put("1", "2");
        multimap.put("1", "4");
        multimap.put("1", "3");

        multimap.keySet().forEach(key -> {
            List<String> values = multimap.get(key);
            System.out.println("Key: " + key + ", Values: " + values);
        });
        System.out.println("multimap------------------------------------------------------");
        //适用于需要保留插入顺序并允许重复值的场景
        ListMultimap<String, String> linkedListMultimap = LinkedListMultimap.create();
        linkedListMultimap.put("3", "4");
        linkedListMultimap.put("2", "4");
        linkedListMultimap.put("4", "4");
        linkedListMultimap.put("2", "3");
        linkedListMultimap.put("1", "2");
        linkedListMultimap.put("1", "4");
        linkedListMultimap.put("1", "3");

        linkedListMultimap.keySet().forEach(key -> {
            List<String> values = linkedListMultimap.get(key);
            System.out.println("Key: " + key + ", Values: " + values);
        });
        System.out.println("linkedListMultimap------------------------------------------------------");

    }
}
