package com.gery.collection.controller.list;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * @program: gery-demo-2023
 * @ClassName MultisetController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-31 15:11
 * @Version 1.0
 **/
public class MultisetController {
    public static void main(String[] args) {
        String[] keys = {"key1", "key2", "key1", "key3", "key2"};

        Multiset<String> multiset = HashMultiset.create();

        for (String key : keys) {
            multiset.add(key);
        }
//        multiset.forEach((key) -> {
//            int count = multiset.count(key);
//            System.out.println("Key: " + key + ", Count: " + count);
//
//        });
//        multiset.forEach(key -> {
//            int count = multiset.count(key);
//            System.out.println("Key: " + key + ", Count: " + count);
//
//        });
        multiset.remove("key1");

        for (String distinctKey : multiset.elementSet()) {
            int count = multiset.count(distinctKey);
            System.out.println("Key: " + distinctKey + ", Count: " + count);
        }
    }
}
