package com.gery.mq.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description: RabbitTemplateConfirmCallback
 * @Author: YaoWenHua
 * @Date: 2022/11/16 11:09
 */
//@Component
@Slf4j
public class RabbitTemplateConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
//指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);

    }


    /**
     * Confirmation callback.
     *
     * @param correlationData correlation data for the callback.
     * @param ack             true for ack, false for nack
     * @param cause           An optional cause, for nack, when available, otherwise null.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息唯一标识：{},确认结果：{},失败原因：{}", correlationData, ack, cause);
    }
}
