package com.gery.redis.common.enums;

/**
 * @Description: LimitType
 * @Author: YaoWenHua
 * @Date: 2022/5/20 18:33
 */
public enum LimitType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,
    /**
     * 根据请求者IP进行限流
     */
    IP
}
