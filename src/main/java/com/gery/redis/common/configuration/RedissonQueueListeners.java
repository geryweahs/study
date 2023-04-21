package com.gery.redis.common.configuration;

import com.gery.redis.listener.RedissonListener;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: RedissonQueueInit
 * @Author: YaoWenHua
 * @Date: 2022/11/10 18:40
 */

@Slf4j
@Component
public class RedissonQueueListeners implements CommandLineRunner {

    @Resource
    private RedissonClient client;
    @Resource
    private List<RedissonListener> redissonListeners;


    /***
     * @Description: startThread
     *
     * @Param: [queueName, redisDelayedQueueListener]
     * @return: void
     * @Author: YaoWenHua
     * @Date: 2022/11/11
     */
    private <T> void startThread(String queueName, RedissonListener redisDelayedQueueListener) {
        RBlockingQueue<T> blockingFairQueue = client.getBlockingQueue(queueName);
        //服务重启后，无offer，take不到信息。
        client.getDelayedQueue(blockingFairQueue);
        //由于此线程需要常驻，可以新建线程，不用交给线程池管理
        Thread thread = new Thread(() -> {
            log.info("启动监听队列线程" + queueName);
            while (true) {
                String take = "";
                try {
                    take = (String) blockingFairQueue.take();
                    log.info("监听队列线程，监听名称：{},内容:{}", queueName, take);
                    redisDelayedQueueListener.invoke(take);
                } catch (Exception e) {
                    log.info("监听队列线程错误,", e);
                    // todo 消费失败，通知管理员
                }

            }
        });
        thread.setName(queueName);
        thread.start();
    }


    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        redissonListeners.forEach(redissonListener -> startThread(redissonListener.getClass().getName(), redissonListener));
    }
}
