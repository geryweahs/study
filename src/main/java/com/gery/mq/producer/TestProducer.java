package com.gery.mq.producer;

import com.alibaba.fastjson.JSON;
import com.gery.mq.enums.QueueEnum;
import com.gery.mq.service.MqSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.postprocessor.MessagePostProcessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
                        message.getMessageProperties().setDelay(20000);
                        return message;
                    });


        } catch (Throwable e) {
            log.error("推送订单{}事件出现异常：", JSON.toJSONString("dto"), e);
        }
    }

}
