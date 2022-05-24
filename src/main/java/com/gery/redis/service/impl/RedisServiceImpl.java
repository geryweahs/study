package com.gery.redis.service.impl;

import com.boss.common.result.BossResponse;
import com.gery.redis.model.RedisTestReq;
import com.gery.redis.service.RedisService;
import org.springframework.stereotype.Service;

/**
 * @Description: RedisServiceImpl
 * @Author: YaoWenHua
 * @Date: 2022/5/23 18:40
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Override
    public BossResponse limitIpTest(RedisTestReq redisTestReq) {
        return null;
    }

    @Override
    public BossResponse limitAllTest(RedisTestReq redisTest) {
        return null;
    }
}
