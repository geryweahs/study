package com.gery.collection.controller.map;

import com.gery.collection.model.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: gery-demo-2023
 * @ClassName SerializeMapController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-18 10:20
 * @Version 1.0
 **/
public class SerializableMapController {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List list=new ArrayList();
        List list2=new ArrayList();
        List list3=new ArrayList();

        list.addAll(list2);




        list2.addAll(list3);

        List<User.Score> scoreList = new ArrayList<>();
        scoreList.add(new User.Score(90, 100));
        scoreList.add(new User.Score(100, 100));
        List<User.Score> scoreList2 = new ArrayList<>();
        scoreList2.add(new User.Score(80, 100));
        scoreList2.add(new User.Score(70, 100));
        User user = new User("1", "1", 1, 1, scoreList);
        User user2 = new User("2", "2", 2, 2, scoreList2);

        Map<String, User> map = new HashMap<>();

        map.put("1", user);
        map.put("2", user2);
        scoreList.forEach(score -> score.setOneScore(91));
        //浅拷贝
        HashMap<String, User> stringUserHashMap = new HashMap<>(map);

        user.setAge(11);
        for (Map.Entry<String, User> stringUserEntry : stringUserHashMap.entrySet()) {
            System.out.println(stringUserEntry);
        }
        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");
        //深拷贝   clone
        Map<String, User> deepCopyMap = new HashMap<>();
        for (Map.Entry<String, User> stringUserEntry : map.entrySet()) {
            User clone = stringUserEntry.getValue().clone();
            deepCopyMap.put(stringUserEntry.getKey(), clone);
        }
        scoreList2.forEach(score -> score.setOneScore(99));
        user2.setAge(55);
        for (Map.Entry<String, User> dep : deepCopyMap.entrySet()) {
            System.out.println(dep);
        }
        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");

        //深拷贝   序列化 TODO   序列化  Util
        //创建一个字节数组输出流，可以将数据写入字节数组中。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //创建一个对象输出流，并将其连接到之前创建的字节数组输出流。对象输出流用于将对象序列化（即将对象转换为字节流）。
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        //将原始的 map 对象通过对象输出流进行序列化，将其写入字节数组输出流中。
        objectOutputStream.writeObject(map);

        //创建一个字节数组输入流，并将其连接到之前创建的字节数组输出流的字节数组。字节数组输入流允许你从字节数组中读取数据。
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        //创建一个对象输入流，并将其连接到字节数组输入流。对象输入流用于从字节流中反序列化对象（即从字节流中还原对象）。
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        //从对象输入流中读取反序列化后的对象，即深拷贝的 map。在这个例子中，map 中的值是 User 类型，因此需要进行类型转换。
        Map<String, User> deepCopyMap2 = (Map<String, User>) objectInputStream.readObject();

        //赋值
        user2.setAge(4);
        scoreList2.forEach(score -> score.setOneScore(100));
        for (Map.Entry<String, User> dep : deepCopyMap2.entrySet()) {
            System.out.println(dep);
        }


    }
}
