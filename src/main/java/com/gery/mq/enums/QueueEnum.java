package com.gery.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: QueueEnum
 * @Author: YaoWenHua
 * @Date: 2022/11/15 18:35
 */
@Getter
@AllArgsConstructor
public enum QueueEnum {


    /**
     * QUEUE_TEST
     */
    QUEUE_TEST("test.direct", "test.queue", "*", "QUEUE_TEST"),


    QUEUE_TEST_01("test.one.direct", "test.one.queue", "*", "QUEUE_TEST_01"),


    QUEUE_TEST_02("test.two.direct", "test.two.queue", "*", "QUEUE_TEST_02"),


    QUEUE_TEST_03("test.three.ttl.direct", "test.three.ttl.queue", "*", "QUEUE_TEST_03");





    /**
     * 交换机
     */
    private final String exchange;

    /**
     * 队列
     */
    private final String queue;

    /**
     * 路由键
     */
    private final String routeKey;

    /**
     * 队列简介
     */
    private final String desc;
}
