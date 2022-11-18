package com.gery.redis.service;

import com.gery.redis.listener.RedissonListener;

import java.util.concurrent.TimeUnit;

/**
 * @Description: RedisQueueService
 * @Author: YaoWenHua
 * @Date: 2022/11/10 18:18
 */
public interface RedisQueueService {

    void addQueue(String message, long delay, TimeUnit timeUnit, String queueName);

    void remove(String message, String queueName);

    void remove(String message, Class<? extends RedissonListener> clazz);

    void addQueue(String message, long delay, Class<? extends RedissonListener> clazz);

}
