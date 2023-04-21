package com.gery.mq.producer;

import com.alibaba.fastjson.JSON;
import com.gery.mq.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @Description: TestProducer
 * @Author: YaoWenHua
 * @Date: 2022/11/16 09:51
 */
@Slf4j
@Component
public class TestProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void sendOrderQueue() {
        log.info("发送插件延迟信息开始");
        try {


            rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TEST.getExchange(),
                    QueueEnum.QUEUE_TEST.getRouteKey(), JSON.toJSONString("dto").getBytes(StandardCharsets.UTF_8), message -> {
                        // 给消息设置延迟毫秒值
                        MessageProperties messageProperties = message.getMessageProperties();
                        messageProperties.setHeader("x-delay",20000);
                      //  messageProperties.setDelay(20000);
                        return message;
                    });


        } catch (Throwable e) {
            log.error("推送订单{}事件出现异常：", JSON.toJSONString("dto"), e);
        }
    }

}
