package com.gery.collection.controller.list;

import cn.hutool.core.collection.CollUtil;
import com.gery.collection.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: gery-demo-2023
 * @ClassName FirstListController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-25 10:16
 * @Version 1.0
 **/
public class FirstListController {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "2", 16, 1, null));
        userList.add(new User("1", "2", 17, 1, null));
        userList.add(new User("3", "4", 15, 1, null));
        userList.add(new User("4", "5", 17, 1, null));
        User user = userList.stream().filter(s -> Objects.equals(s.getAge(), 16)).findFirst().get();
        //迭代器的方式
        Object first = CollUtil.getFirst(userList);

        System.out.println(first);
    }
}
