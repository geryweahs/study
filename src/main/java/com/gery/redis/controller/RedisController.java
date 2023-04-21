package com.gery.redis.controller;

import com.alibaba.fastjson.JSON;
import com.gery.redis.common.annotation.RateLimiter;
import com.gery.redis.common.enums.LimitType;
import com.gery.redis.listener.OrderDelayedListener;
import com.gery.redis.listener.TestListener;
import com.gery.redis.model.OrderInfo;
import com.gery.redis.model.RedisTestReq;
import com.gery.redis.service.RedisService;
import com.gery.redis.service.impl.RedisQueueServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;



/**
 * @Description: RedisController
 * @Author: YaoWenHua
 * @Date: 2022/5/23 18:33
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "redis接口")
@Slf4j
public class RedisController {

    @Autowired
    private RedisService redisService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisQueueServiceImpl redisQueueService;

    @ApiOperation(value = "IP限流接口", tags = "redis信息接口")
    @PostMapping(value = "/ipLimit")
    @RateLimiter(time = 60, count = 3, limitType = LimitType.IP)
    public String limitIpTest(@RequestBody RedisTestReq redisTestReq) {
        return "";
    }

    @ApiOperation(value = "全局限流接口", tags = "redis信息接口")
    @PostMapping(value = "/allLimit")
    @RateLimiter(time = 60, count = 3, limitType = LimitType.DEFAULT)
    public String limitAllTest(@RequestBody RedisTestReq redisTest) {
        return redisService.limitAllTest(redisTest);
    }


    String maotai = "maotai20210321001";//茅台商品编号

    @PostConstruct
    public void init() {
        //此处模拟向缓存中存入商品库存操作
        redisTemplate.opsForValue().set(maotai, "100");
    }


    @GetMapping("/get/maotai2")
    public String seckillMaotai2() {
        //     synchronized (this) {
        Integer count = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(maotai))); // 1
        //如果还有库存
        if (count > 0) {
            //抢到了茅台，库存减一
            redisTemplate.opsForValue().set(maotai, String.valueOf(count - 1));
            //后续操作 do something
            String s = redisTemplate.opsForValue().get(maotai);
            log.info("我抢到茅台了! 剩余" + s + "台");

            return "ok";
        } else {
            return "no";
        }
        //}
    }

    @Autowired
    private RedissonClient redissonClient;


    @PostMapping(value = "redissonTest")
    public String redissonTest01() {
        redisQueueService.addQueue("消费30秒", 12L, TimeUnit.SECONDS, TestListener.class.getName());
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setMessage("消费1分钟");
        String jsonString = JSON.toJSONString(orderInfo);
        redisQueueService.addQueue(jsonString, 10L, OrderDelayedListener.class);
//        redisQueueService.remove("消费30秒","test-01");
//        redisQueueService.remove("消费1分钟", OrderDelayedListener.class);
        return "成功";

    }


    @PostMapping(value = "redisson")
    public String redissonTest() {

        RBlockingQueue<OrderInfo> blockingFairQueue = redissonClient.getBlockingQueue("my-test");

        RDelayedQueue<OrderInfo> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);

        OrderInfo orderInfo = new OrderInfo();
        // 订单生成时间
        orderInfo.setCreateTime(new Date());
        LocalDateTime now = LocalDateTime.now();
        // 10秒钟以后将消息发送到指定队列
        delayedQueue.offer(orderInfo, 20, TimeUnit.SECONDS);
        // RBlockingQueue<OrderInfo> outQueue = redissonClient.getBlockingQueue("my-test");
        System.out.println("订单生成时间" + now);
        // 在该对象不再需要的情况下，应该主动销毁。仅在相关的Redisson对象也需要关闭的时候可以不用主动销毁

        return "1";
    }

    public void delayTaskHandler() {
        RBlockingQueue<OrderInfo> blockingFairQueue = redissonClient.getBlockingQueue("my-test");
        while (true) {
            try {
                OrderInfo value = blockingFairQueue.take();
                System.out.println("订单关闭时间" + LocalDateTime.now());
                log.info("delay queue {},延时任务开始执行,value - {} , timeStamp - {} , threadName - {}", blockingFairQueue, value, System.currentTimeMillis(), Thread.currentThread().getName());
                //consumer.accept(value);
            } catch (Exception e) {
                log.error("延时任务执行失败,", e);
            }
        }

    }

}
