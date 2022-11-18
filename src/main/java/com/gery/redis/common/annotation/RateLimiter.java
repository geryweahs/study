package com.gery.redis.common.annotation;

import com.gery.redis.common.enums.LimitType;

import java.lang.annotation.*;

/**
 * @Description: RateLimiter
 * @Author: YaoWenHua
 * @Date: 2022/5/20 18:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    /**
     * 限流key
     */
    String key() default "rate_limit:";

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 10;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;

}
