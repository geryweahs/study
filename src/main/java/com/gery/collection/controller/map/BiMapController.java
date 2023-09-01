package com.gery.collection.controller.map;

import com.gery.collection.model.User;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @program: gery-demo-2023
 * @ClassName BiMapController
 * @description:
 * @author: yaowenhua
 * @create: 2023-09-01 09:35
 * @Version 1.0
 **/
public class BiMapController {
    public static void main(String[] args) {
        //1.适用于变更的时候记录
        //2. key value 查询


        //1
        BiMap<String, String> biMap = HashBiMap.create();
        Object put = biMap.put("2", "3");
        System.out.println(put);
        Object put2 = biMap.put("2", "4");
        System.out.println(put2);
        String put1 = biMap.put("3", "4");
        System.out.println(put1);
        String s = biMap.inverse().get("4");
        System.out.println(s);

//        BiMap<String, User> userBiMap = HashBiMap.create();
//        userBiMap.put("key",new User("1", "1", 1, 1, null));
//        User key1 = userBiMap.put("key", new User("2", "3", 1, 1, null));
//        System.out.println(key1);
//
//
//        //2
//        String value = biMap.get("2");
//        System.out.println("value:"+value);
//          String key = biMap.inverse().get("4");
//        System.out.println("key:"+key);



    }
}
