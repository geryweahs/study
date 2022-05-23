package com.gery;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.tsf.annotation.EnableTsf;

/**
 * @Description: StrategyApplication
 * @Author: YaoWenHua
 * @Date: 2022/5/23 10:24
 */
@EnableFeignClients
@SpringBootApplication
@MapperScan(value = {"com.gery.*.mapper"})
@EnableTsf
@Slf4j
public class RedisGreyApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisGreyApplication.class, args);
        log.info("服务启动成功！！！");
    }
}
