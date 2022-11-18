package com.gery.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: TestListener
 * @Author: YaoWenHua
 * @Date: 2022/11/9 18:30
 */
@Slf4j
@Component
public class TestListener implements RedissonListener {
    @Override
    public void invoke(String s) {
        log.info("test:{}", s);
    }
}
