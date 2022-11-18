package com.gery.redis.listener;

import com.gery.redis.model.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: OrderDelayedListener
 * @Author: YaoWenHua
 * @Date: 2022/11/9 18:28
 */
@Slf4j
@Component
public class OrderDelayedListener implements RedissonListener {
    @Override
    public void invoke(String message) {
        log.info("我被消费了：{}", message);
    }
}
