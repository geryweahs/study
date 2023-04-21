package com.gery.redis.service.impl;

import com.gery.redis.listener.RedissonListener;
import com.gery.redis.service.RedisQueueService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description: RedisQueue
 * @Author: YaoWenHua
 * @Date: 2022/11/10 18:12
 */
@Component
@Slf4j
public class RedisQueueServiceImpl implements RedisQueueService {

    @Autowired
    private RedissonClient redissonClient;


    public void addQueue(String message, long delay, TimeUnit timeUnit, String queueName) {
        log.info("添加延迟队列,监听名称:{},时间:{},时间单位:{},内容:{}", queueName, delay, timeUnit, message);
        RBlockingDeque<String> blockingDeque = redissonClient.getBlockingDeque(queueName);
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        delayedQueue.offer(message, delay, timeUnit);
    }

    public void remove(String message, String queueName) {
        RBlockingDeque<String> blockingDeque = redissonClient.getBlockingDeque(queueName);
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        delayedQueue.remove(message);
    }

    public void remove(String message, Class<? extends RedissonListener> clazz) {
        remove(message, clazz.getName());
    }

    public void addQueue(String message, long delay, Class<? extends RedissonListener> clazz) {
        addQueue(message, delay, TimeUnit.SECONDS, clazz.getName());


    }

}
