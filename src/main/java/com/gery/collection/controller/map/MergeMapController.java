package com.gery.collection.controller.map;

import com.gery.collection.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: gery-demo-2023
 * @ClassName MergeMapController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-15 16:19
 * @Version 1.0
 **/
public class MergeMapController {
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();
        HashMap<Integer, Integer> typeMap = new HashMap<>();
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "2", 16, 1, null));
        userList.add(new User("1", "2", 17, 1, null));
        userList.add(new User("3", "4", 15, 2, null));
        userList.add(new User("4", "5", 17, 1, null));

        for (User user : userList) {
            hashMap.merge(user.getId(), user.getCode(), String::concat);

            typeMap.merge(user.getType(), user.getAge(), Integer::max);
        }

        System.out.println(hashMap);
        System.out.println(typeMap);
    }
}
