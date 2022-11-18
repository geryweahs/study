package com.gery.mq.producer;

import com.alibaba.fastjson.JSON;
import com.gery.mq.enums.QueueEnum;
import com.gery.mq.service.MqSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @Description: Test02Producer
 * @Author: YaoWenHua
 * @Date: 2022/11/18 09:46
 */
@Slf4j
@Component
public class Test02Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void sendOrderQueue() {
        for (int i = 0; i < 5; i++) {
            log.info("发送ttl信息开始：{}", i);
            try {

                rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TEST_02.getExchange(),
                        QueueEnum.QUEUE_TEST_02.getRouteKey(), JSON.toJSONString("dto" + i).getBytes(StandardCharsets.UTF_8));
            } catch (Throwable e) {
                log.error("推送订单{}事件出现异常：", JSON.toJSONString("dto"), e);
            }
        }

    }

}
