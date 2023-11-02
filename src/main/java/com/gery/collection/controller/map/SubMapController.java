package com.gery.collection.controller.map;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @program: gery-demo-2023
 * @ClassName SubMapController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-15 11:13
 * @Version 1.0
 **/
public class SubMapController {

    public static void main(String[] args) {
        //leftNum   包含     rightNum   不包含
        Integer leftNum = 3;
        Integer rightNum = 10;

        //四参数
        NavigableMap<Integer, Integer> navigableMap = new TreeMap<>();
        navigableMap.put(3, 2);
        NavigableMap<Integer, Integer> subMap = navigableMap.subMap(leftNum, false, rightNum, false);
        SortedMap<Integer, Integer> subMap1 = navigableMap.subMap(leftNum, rightNum);


        //两参数
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
        SortedMap<Integer, Integer> subMap2 = sortedMap.subMap(leftNum, rightNum);


    }
}
