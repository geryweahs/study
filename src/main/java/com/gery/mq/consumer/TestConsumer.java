package com.gery.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.boss.common.result.BossResponse;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: TestConsumer
 * @Author: YaoWenHua
 * @Date: 2022/11/16 09:51
 */
@Slf4j
@Component
public class TestConsumer {


    @RabbitHandler
    @RabbitListener(queues = "test.queue")
    public void handle(byte[] bytes, Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("2");
        }
        String object = new String(bytes);
        log.info("延时订单接收消息:{}", object);

    }
}
