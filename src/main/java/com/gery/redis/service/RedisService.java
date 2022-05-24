package com.gery.redis.service;

import com.boss.common.result.BossResponse;
import com.gery.redis.model.RedisTestReq;

/**
 * @Description: RedisService
 * @Author: YaoWenHua
 * @Date: 2022/5/23 18:40
 */
public interface RedisService {
    BossResponse limitIpTest(RedisTestReq redisTestReq);

    BossResponse limitAllTest(RedisTestReq redisTest);
}
