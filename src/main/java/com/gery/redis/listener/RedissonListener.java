package com.gery.redis.listener;

/**
 * @Description: RedissonListener
 * @Author: YaoWenHua
 * @Date: 2022/11/9 18:26
 */
public interface RedissonListener {

    void invoke(String  message);

}
