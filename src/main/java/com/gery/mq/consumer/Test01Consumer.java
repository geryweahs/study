package com.gery.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.gery.mq.model.BaseMessage;
import com.gery.mq.model.dto.TestMessageDto;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description: Test01Consumer
 * @Author: YaoWenHua
 * @Date: 2022/11/16 17:58
 */
@Slf4j
@Component
public class Test01Consumer {
    @Resource
    private RedisTemplate redisTemplate;

    @RabbitHandler
    @RabbitListener(queues = "test.one.queue")
    public void handle(byte[] bytes, Channel channel, Message message) {
        String receiveMessage = new String(bytes);
        TestMessageDto parse = JSON.parseObject(receiveMessage, TestMessageDto.class);
        String correlationDataId = parse.getCorrelationDataId();
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("消息唯一标识:{}", deliveryTag);
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            try {
                ValueOperations valueOperations = redisTemplate.opsForValue();
                Integer dto = (Integer) valueOperations.get("testQueue:" + correlationDataId);
                if (Objects.nonNull(dto) && dto == 3) {
                    channel.basicReject(deliveryTag, false);
                } else {
                    if (dto == null) {
                        valueOperations.set("testQueue:" + correlationDataId, 1);
                    } else {
                        dto = dto + 1;
                        valueOperations.set("testQueue:" + correlationDataId, dto);
                    }
                    log.info("重回队列的唯一标识:{}", deliveryTag);
                    channel.basicNack(deliveryTag, false, true);
                }
            } catch (Exception e1) {
                log.info("消息重发异常");
            }
            e.printStackTrace();
            throw new RuntimeException("2");
        }


    }
}
