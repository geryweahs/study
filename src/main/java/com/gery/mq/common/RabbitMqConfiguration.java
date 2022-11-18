package com.gery.mq.common;


import com.gery.mq.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfiguration {

    /**
     * 配置单 Exchange Bean
     */
    @Bean
    public CustomExchange testDirect() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(QueueEnum.QUEUE_TEST.getExchange(), "x-delayed-message", true, false, args);
    }


    /**
     * 配置单 Queue Bean
     */
    @Bean
    public Queue testQueue() {
        return new Queue(QueueEnum.QUEUE_TEST.getQueue());
    }


    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(testQueue()).to(testDirect()).with(QueueEnum.QUEUE_TEST.getRouteKey()).noargs();
    }


    /**
     * 配置单 Exchange Bean
     */
    @Bean
    public DirectExchange test01Direct() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.QUEUE_TEST_01.getExchange()).durable(true).build();
    }

    /**
     * 配置单 Queue Bean
     */
    @Bean
    public Queue test01Queue() {
        return new Queue(QueueEnum.QUEUE_TEST_01.getQueue());
    }


    @Bean
    public Binding test01Binding() {
        return BindingBuilder.bind(test01Queue()).to(test01Direct()).with(QueueEnum.QUEUE_TEST_01.getRouteKey());
    }


    //路由ttl消息交换机
    @Bean
    public DirectExchange test02Direct() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.QUEUE_TEST_02.getExchange()).durable(true).build();
    }

    /**
     * 配置单 Queue Bean
     */
    @Bean
    public Queue test02Queue() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("x-message-ttl", 20000);//队列中所有消息5秒后过期
        map.put("x-dead-letter-exchange", QueueEnum.QUEUE_TEST_03.getExchange());//过期后进入死信队列
        return new Queue(QueueEnum.QUEUE_TEST_02.getQueue(), true, false, false, map);
    }


    @Bean
    public Binding test02Binding() {
        return BindingBuilder.bind(test02Queue()).to(test02Direct()).with(QueueEnum.QUEUE_TEST_02.getRouteKey());
    }

    //路由ttl消息交换机
    @Bean
    public DirectExchange test03Direct() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.QUEUE_TEST_03.getExchange()).durable(true).build();
    }

    /**
     * 配置单 Queue Bean
     */
    @Bean
    public Queue test03Queue() {
        return new Queue(QueueEnum.QUEUE_TEST_03.getQueue());
    }


    @Bean
    public Binding test03Binding() {
        return BindingBuilder.bind(test03Queue()).to(test03Direct()).with(QueueEnum.QUEUE_TEST_03.getRouteKey());
    }
}
