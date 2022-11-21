package com.gery.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description: Test01Consumer
 * @Author: YaoWenHua
 * @Date: 2022/11/16 17:58
 */
@Slf4j
@Component
public class Test02Consumer {

    @RabbitHandler
    @RabbitListener(queues = "test.three.ttl.queue")
    public void handle(byte[] bytes, Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("ttl延迟消息:{}", new String(bytes));
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//          //  throw new RuntimeException("1");
//        } catch (Exception e) {
//            try {
//                ValueOperations valueOperations = redisTemplate.opsForValue();
//                Integer dto = (Integer) valueOperations.get("testQueue7:");
//                if (Objects.nonNull(dto) && dto == 3) {
//                    channel.basicReject(deliveryTag, false);
//                } else {
//                    if (dto == null) {
//                        valueOperations.set("testQueue7:", 1);
//                    } else {
//                        dto = dto + 1;
//                        valueOperations.set("testQueue7:", dto);
//                    }
//                    log.info("重回队列的唯一标识:{}", deliveryTag);
//                    channel.basicNack(deliveryTag, false, true);
//                }
//            } catch (Exception e1) {
//                log.info("消息重发异常");
//            }
//            e.printStackTrace();
//            throw new RuntimeException("2");
//        }


    }
}
