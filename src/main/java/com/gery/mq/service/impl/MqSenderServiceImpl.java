package com.gery.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.gery.mq.enums.QueueEnum;
import com.gery.mq.model.BaseMessage;
import com.gery.mq.model.dto.TestMessageDto;
import com.gery.mq.service.MqSenderService;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.core.annotations.Nullable;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @Description: MqSenderServiceImpl
 * @Author: YaoWenHua
 * @Date: 2022/11/18 10:40
 */
@Service
@Slf4j
public class MqSenderServiceImpl implements MqSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //确认消息到达MQ,根据实际业务场景处理
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        log.info("=============SERVICE:::confirmCallBack触发。消息到达MQ broker===========");
        log.info("correlationDataID:{}", correlationData.getId());
        log.info("ack:{}", ack);
        log.info("cause:{}", cause);
        if (!ack) {
            log.info("异常处理。。。。");
        }
    };

    //配置return监听处理，消息无法路由到queue,根据实际业务操作
    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        log.info("=============SERVICE:::returnCallback触发。消息路由到queue失败===========");
        log.info("msg:{}", new String(message.getBody()));
        log.info("replyCode:{}", replyCode);
        log.info("replyText:{}", replyText);
        log.info("exchange:{}", exchange);
        log.info("routingKey:{}", routingKey);
    };


    public void sendMessage(String exchange, String routingKey, BaseMessage message) {
        //设置消息的confirm监听，监听消息是否到达exchange
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //设置消息的return监听，当消息无法路由到queue时候，会触发这个监听。
        rabbitTemplate.setReturnCallback(returnCallback);
        //correlationDataId相当于消息的唯一表示
        UUID correlationDataId = UUID.randomUUID();
        CorrelationData correlationData = new CorrelationData(correlationDataId.toString());
        message.setCorrelationDataId(correlationDataId.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, JSON.toJSONString(message).getBytes(StandardCharsets.UTF_8), correlationData);
    }

    public void sendMessage(String exchange, String routingKey, BaseMessage baseMessage, Integer delay) {
        //设置消息的confirm监听，监听消息是否到达exchange
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //设置消息的return监听，当消息无法路由到queue时候，会触发这个监听。
        rabbitTemplate.setReturnCallback(returnCallback);
        //correlationDataId相当于消息的唯一表示
        UUID correlationDataId = UUID.randomUUID();
        CorrelationData correlationData = new CorrelationData(correlationDataId.toString());
        baseMessage.setCorrelationDataId(correlationDataId.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, JSON.toJSONString(baseMessage).getBytes(StandardCharsets.UTF_8), message -> {
            // 给消息设置延迟毫秒值
            message.getMessageProperties().setDelay(delay);
            return message;
        }, correlationData);
    }

    /***
     * @Description: sendMessage
     *
     * @Param: [queueEnum, messageDto]
     * @return: void
     * @Author: YaoWenHua
     * @Date: 2022/11/18
     * @param queueEnum
     * @param messageDto
     */
    @Override
    public void sendMessage(QueueEnum queueEnum, TestMessageDto messageDto) {
        //设置消息的confirm监听，监听消息是否到达exchange
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //设置消息的return监听，当消息无法路由到queue时候，会触发这个监听。
        rabbitTemplate.setReturnCallback(returnCallback);
        //correlationDataId相当于消息的唯一表示
        UUID correlationDataId = UUID.randomUUID();
        CorrelationData correlationData = new CorrelationData(correlationDataId.toString());
        messageDto.setCorrelationDataId(correlationDataId.toString());
        rabbitTemplate.convertAndSend(queueEnum.getExchange(), queueEnum.getRouteKey(), JSON.toJSONString(messageDto).getBytes(StandardCharsets.UTF_8), correlationData);
    }

    /***
     * @Description: sendMessage
     *
     * @Param: [queueEnum, messageDto, delay]
     * @return: void
     * @Author: YaoWenHua
     * @Date: 2022/11/18
     * @param queueEnum
     * @param messageDto
     * @param delay
     */
    @Override
    public void sendMessage(QueueEnum queueEnum, TestMessageDto messageDto, Integer delay) {
        //设置消息的confirm监听，监听消息是否到达exchange
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //设置消息的return监听，当消息无法路由到queue时候，会触发这个监听。
        rabbitTemplate.setReturnCallback(returnCallback);
        //correlationDataId相当于消息的唯一表示
        UUID correlationDataId = UUID.randomUUID();
        CorrelationData correlationData = new CorrelationData(correlationDataId.toString());
        messageDto.setCorrelationDataId(correlationDataId.toString());
        rabbitTemplate.convertAndSend(queueEnum.getExchange(), queueEnum.getRouteKey(), JSON.toJSONString(messageDto).getBytes(StandardCharsets.UTF_8), message -> {
            // 给消息设置延迟毫秒值
            message.getMessageProperties().setDelay(delay);
            return message;
        }, correlationData);
    }

}
