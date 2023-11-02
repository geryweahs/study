package com.gery.mq.controller;

import com.gery.mq.enums.KafkaContains;
import com.gery.mq.producer.KafKa01Producer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.CreatePartitionsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewPartitions;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.gery.mq.enums.KafkaContains.GROUP_ID;
import static com.gery.mq.enums.KafkaContains.LOCAL_KAFKA_SERVER;
import static com.gery.mq.enums.KafkaContains.NEW_TOPIC;

/**
 * @program: gery-demo-2023
 * @ClassName KafakaController
 * @description:
 * @author: yaowenhua
 * @create: 2023-10-24 11:20
 * @Version 1.0
 **/
@RestController
@RequestMapping("/KafKa")
@Api(tags = "rabbit接口")
@Slf4j
public class KafkaController {

    @Autowired
    private KafKa01Producer kafKa01Producer;


    @GetMapping(value = "send")
    public String rabbitTest(@RequestParam("message") String message) {
        kafKa01Producer.sendMessage(message);
        return "ok";
    }

    @GetMapping(value = "get")
    public String getPatitionList() {
        // 创建 AdminClient 实例
        Properties props = new Properties();
        props.put("bootstrap.servers", LOCAL_KAFKA_SERVER);
        AdminClient adminClient = AdminClient.create(props);

        // 获取主题的描述信息
        DescribeTopicsResult topicsResult = adminClient.describeTopics(Collections.singletonList(NEW_TOPIC));

        try {
            Map<String, KafkaFuture<TopicDescription>> topicDescriptionFutures = topicsResult.values();
            KafkaFuture<TopicDescription> topicDescriptionFuture = topicDescriptionFutures.get(NEW_TOPIC);
            TopicDescription topicDescription = topicDescriptionFuture.get();

            int partitionCount = topicDescription.partitions().size();
            System.out.println("主题 " + NEW_TOPIC + " 的分区数量是: " + partitionCount);

            // 输出分区编码
            for (TopicPartitionInfo partition : topicDescription.partitions()) {
                System.out.println("分区编码: " + partition.partition() + ", 分区领导者: " + partition.leader().id());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭 AdminClient
        adminClient.close();
        return "success";

    }


    @GetMapping(value = "poll")
    public String pollTest() {
        // 配置消费者属性
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaContains.LOCAL_KAFKA_SERVER);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 创建Kafka消费者
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
//         List<TopicPartition> topicPartitionList = new ArrayList<>();
//         topicPartitionList.add(new TopicPartition(NEW_TOPIC, 1));
//         consumer.assign(topicPartitionList);
//         // 订阅主题
        List<String> strings = Collections.singletonList(NEW_TOPIC);
        consumer.subscribe(strings);

        // 主动拉取消息，等待最多10秒
        while (true) {
            try {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    System.out.println(record.key() + "---------" + record.value());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//          ConsumerRecords<String, String> records2 = consumer.poll(Duration.ofSeconds(10));
//         if(CollUtil.isNotEmpty(records2)){
//             // 处理收到的消息
//             records2.forEach(record -> System.out.printf("Received message: key=%s, value=%s%n", record.key(), record.value()));
//         }else {
//             log.info("未拉取到任何消息{}, {}",GROUP_ID,NEW_TOPIC);
//         }


        // 关闭消费者


    }


}
