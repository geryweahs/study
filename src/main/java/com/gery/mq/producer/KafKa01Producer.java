package com.gery.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static com.gery.mq.enums.KafkaContains.NEW_TOPIC;

/**
 * @program: gery-demo-2023
 * @ClassName KafKa01Producer
 * @description:
 * @author: yaowenhua
 * @create: 2023-10-24 11:19
 * @Version 1.0
 **/
@Service
@Slf4j
public class KafKa01Producer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(NEW_TOPIC,message);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
               log.error("发送失败", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringStringSendResult) {
                log.info("发送成功:{}",stringStringSendResult.toString());
            }
        });
    }

}
