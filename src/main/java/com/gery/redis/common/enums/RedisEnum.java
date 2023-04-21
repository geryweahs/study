package com.gery.redis.common.enums;


/**
 * @Description: RedisEnum
 * @Author: YaoWenHua
 * @Date: 2022/5/25 10:11
 */
public enum RedisEnum  {
    Exception_01(1001, "普通异常");


    private Integer code;

    private String value;

    RedisEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }




}
