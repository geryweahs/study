package com.gery.mq.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description: RabbitTemplateReturnCallback
 * @Author: YaoWenHua
 * @Date: 2022/11/16 11:10
 */
//@Component
@Slf4j
public class RabbitTemplateReturnCallback implements RabbitTemplate.ReturnCallback {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        //指定 ReturnCallback

        rabbitTemplate.setReturnCallback(this);

        //rabbitTemplate.setMandatory(true);

    }

    /**
     * Returned message callback.
     *
     * @param message    the returned message.
     * @param replyCode  the reply code.
     * @param replyText  the reply text.
     * @param exchange   the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        //只有 路由不到队列时 才会出发return机制
        log.info("消息主体 message : " + message);


        log.info("消息主体 message : " + replyCode);


        log.info("描述：" + replyText);


        log.info("消息使用的交换器 exchange : " + exchange);


        log.info("消息使用的路由键 routing : " + routingKey);


    }
}
