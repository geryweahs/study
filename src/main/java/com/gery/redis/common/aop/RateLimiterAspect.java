package com.gery.redis.common.aop;

import com.boss.common.constant.BossConstants;
import com.boss.common.context.BossContext;
import com.boss.common.enums.BaseErrorEnum;
import com.boss.common.exception.BossException;
import com.gery.redis.common.annotation.RateLimiter;
import com.gery.redis.common.enums.LimitType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static com.gery.redis.common.enums.RedisEnum.Exception_01;

/**
 * @Description: RateLimiterAspect
 * @Author: YaoWenHua
 * @Date: 2022/5/20 18:36
 */
@Aspect
@Component
public class RateLimiterAspect {
    private static final Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RedisScript<Long> limitScript;

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
        String key = rateLimiter.key();
        int time = rateLimiter.time();
        int count = rateLimiter.count();

        String combineKey = getCombineKey(rateLimiter, point);
        List<Object> keys = Collections.singletonList(combineKey);
        try {
            Long number = redisTemplate.execute(limitScript, keys, count, time);
            if (number == null || number.intValue() > count) {
                BaseErrorEnum baseErrorEnum = new BaseErrorEnum() {
                    @Override
                    public Integer getCode() {
                        return BossConstants.FAIL;
                    }

                    @Override
                    public String getName() {
                        return "访问过于频繁，请稍候再试";
                    }
                };
                throw new BossException(baseErrorEnum);

            }
            log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), key);
        } catch (RuntimeException e) {
            throw new BossException(Exception_01);

        } catch (Exception e) {
            throw new RuntimeException("服务器限流异常，请稍候再试");
        }
    }

    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuilder stringBuffer = new StringBuilder(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP) {
                stringBuffer.append(BossContext.getAccountId());
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }


}
