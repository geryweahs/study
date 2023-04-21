package com.gery.redis.service;

import com.gery.redis.model.RedisTestReq;

/**
 * @Description: RedisService
 * @Author: YaoWenHua
 * @Date: 2022/5/23 18:40
 */
public interface RedisService {
    String limitIpTest(RedisTestReq redisTestReq);

    String limitAllTest(RedisTestReq redisTest);
}
