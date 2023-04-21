package com.gery.mq.producer;

import com.alibaba.fastjson.JSON;
import com.gery.mq.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @Description: Test01Producer
 * @Author: YaoWenHua
 * @Date: 2022/11/16 17:56
 */
@Slf4j
@Component
public class Test01Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void sendOrderQueue() {
        log.info("发送普通信息开始");
        try {
            rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TEST_01.getExchange(),
                    QueueEnum.QUEUE_TEST_01.getRouteKey(), JSON.toJSONString("dto").getBytes(StandardCharsets.UTF_8));


        } catch (Throwable e) {
            log.error("推送订单{}事件出现异常：", JSON.toJSONString("dto"), e);
        }
    }

}
