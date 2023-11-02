package com.gery.collection.controller.list;

import com.gery.collection.model.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gery-demo-2023
 * @ClassName ConvertListController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-25 16:43
 * @Version 1.0
 **/
public class ConvertListController {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "2", 16, 1, null));
        userList.add(new User("1", "2", 17, 1, null));
        userList.add(new User("3", "4", 15, 1, null));
        userList.add(new User("4", "5", 17, 1, null));
        ArrayList<User> users = Lists.newArrayList(userList);
        for (User user : users) {
            user.setAge(99);
        }
        System.out.println(users);

        System.out.println(userList);

        List<User> users1 = new ArrayList<>(userList);

        for (User user : users1) {
            user.setAge(100);
        }
        System.out.println(users1);

        System.out.println(userList);
        String value = "value.key";
        ImmutableList<String> strings = ImmutableList.copyOf(value.split("\\."));
        List<String> arrayList = new ArrayList<>(strings);
        System.out.println(arrayList);


        ImmutableList<User> users2 = ImmutableList.copyOf(userList);
        userList.add(new User("5", "6", 17, 1, null));
        for (User user : users2) {
            user.setAge(11);
        }
        System.out.println(users2);

        System.out.println(userList);
    }
}
