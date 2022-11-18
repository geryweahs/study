package com.gery.redis.handler;

import com.gery.redis.listener.RedissonListener;
import com.gery.redis.model.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Description: RedisDelayHandle
 * @Author: YaoWenHua
 * @Date: 2022/11/9 17:37
 */
//
//@Component
//@Slf4j
//public class RedisDelayHandle {
//    @Autowired
//    private RedissonClient redissonClient;
//
//}
