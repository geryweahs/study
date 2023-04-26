package com.gery;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Description: StrategyApplication
 * @Author: YaoWenHua
 * @Date: 2022/5/23 10:24
 */
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@MapperScan(value = {"com.gery.*.mapper"})
@Slf4j
public class RedisGreyApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisGreyApplication.class, args);
        log.info("服务启动成功！！！");
    }
}
