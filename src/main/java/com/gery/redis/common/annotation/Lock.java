package com.gery.redis.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Lock
 * @Author: YaoWenHua
 * @Date: 2022/10/20 14:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Lock {

    /**
     * 锁定的目标
     *
     * @return
     */
    String target() default "";

    /**
     * 有效时间
     *
     * @return
     */
    long expire() default 60L;

    /**
     * 时间单位
     *
     * @return
     */
    TimeUnit timUnit() default TimeUnit.SECONDS;
}
