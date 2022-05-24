package com.gery.redis.controller;

import com.boss.common.result.BossResponse;
import com.gery.redis.common.enums.LimitType;
import com.gery.redis.common.interfaces.RateLimiter;
import com.gery.redis.model.RedisTestReq;
import com.gery.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description: RedisController
 * @Author: YaoWenHua
 * @Date: 2022/5/23 18:33
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "redis接口")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "IP限流接口", tags = "redis信息接口")
    @PostMapping(value = "/ipLimit")
    @RateLimiter(time = 60,count = 3,limitType = LimitType.IP)
    public BossResponse limitIpTest(@RequestBody RedisTestReq redisTestReq) {
        return redisService.limitIpTest(redisTestReq);
    }

    @ApiOperation(value = "全局限流接口", tags = "redis信息接口")
    @PostMapping(value = "/allLimit")
    @RateLimiter(time = 60,count = 3,limitType = LimitType.DEFAULT)
    public BossResponse limitAllTest(@RequestBody RedisTestReq redisTest) {
        return redisService.limitAllTest(redisTest);
    }

}
