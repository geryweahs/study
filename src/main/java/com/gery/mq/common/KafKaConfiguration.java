package com.gery.mq.common;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.gery.mq.enums.KafkaContains.LOCAL_KAFKA_SERVER;
import static com.gery.mq.enums.KafkaContains.NEW_TOPIC;

/**
 * @program: gery-demo-2023
 * @ClassName KafKaConfiguration
 * @description:
 * @author: yaowenhua
 * @create: 2023-10-24 10:57
 * @Version 1.0
 **/

public class KafKaConfiguration {


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", LOCAL_KAFKA_SERVER);
        KafkaAdmin kafkaAdmin = new KafkaAdmin(configs);
        kafkaAdmin.createOrModifyTopics(new NewTopic(NEW_TOPIC, 50, (short)1));
        return kafkaAdmin;
    }

}
