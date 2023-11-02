package com.gery.mq.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * @program: gery-demo-2023
 * @ClassName Kafka01Consumer
 * @description:
 * @author: yaowenhua
 * @create: 2023-10-24 11:20
 * @Version 1.0
 **/
@Service
public class Kafka01Consumer {
    //topics：指定要监听的 Kafka 主题名称，可以是一个字符串数组，允许同时监听多个主题。
    //groupId：指定消费者组的标识。
    //其他属性，如id、containerFactory 等，用于更高级的配置。
    //如果你需要使用正则表达式匹配多个主题   topicPattern = "new-topic-\\d+"
    @KafkaListener( groupId = "new-group",topicPartitions={@TopicPartition(topic = "new-topic", partitions = { "0" })})

    public void consumer0(String message, Acknowledgment acknowledgment) {
        // 处理接收到的消息
        System.out.println("分区0收到消息" + message);
        acknowledgment.acknowledge();
    }

    @KafkaListener( groupId = "new-group",topicPartitions={@TopicPartition(topic = "new-topic", partitions = { "1" })})
    public void consumer1(String message, Acknowledgment acknowledgment) {
        // 处理接收到的消息
        System.out.println("分区1收到消息" + message);
        acknowledgment.acknowledge();
    }
    @KafkaListener( groupId = "new-group",topicPartitions={@TopicPartition(topic = "new-topic", partitions = { "1" })})
    public void consumer12(String message, Acknowledgment acknowledgment) {
        // 处理接收到的消息
        System.out.println("分区1-2收到消息" + message);
        acknowledgment.acknowledge();
    }
    @KafkaListener( groupId = "new-group-2",topicPartitions={@TopicPartition(topic = "new-topic", partitions = { "1" })})
    public void consumer11(String message, Acknowledgment acknowledgment) {
        // 处理接收到的消息
        System.out.println("分区1-1收到消息" + message);
        acknowledgment.acknowledge();
    }
    @KafkaListener( groupId = "new-group",topicPartitions={@TopicPartition(topic = "new-topic", partitions = { "2" })})
    public void consumer2(String message, Acknowledgment acknowledgment) {
        // 处理接收到的消息
        System.out.println("分区2收到消息" + message);
        acknowledgment.acknowledge();
    }
}
