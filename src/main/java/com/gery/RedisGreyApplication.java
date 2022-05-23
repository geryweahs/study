package com.gery;

/**
 * @Description: RedisGreyApplication
 * @Author: YaoWenHua
 * @Date: 2022/5/23 15:54
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.grey"})
public class RedisGreyApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataFactoryApplication.class, args);
    }
}
