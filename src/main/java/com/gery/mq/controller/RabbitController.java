package com.gery.mq.controller;

import com.alibaba.fastjson.JSON;
import com.gery.mq.model.Req.NumberReq;
import com.gery.mq.producer.Test01Producer;
import com.gery.mq.producer.Test02Producer;
import com.gery.mq.producer.TestProducer;
import com.gery.mq.producer.TestSendProduce;
import com.gery.mq.service.MqSenderService;
import com.gery.redis.listener.OrderDelayedListener;
import com.gery.redis.listener.TestListener;
import com.gery.redis.model.OrderInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @Description: RabbitController
 * @Author: YaoWenHua
 * @Date: 2022/11/16 09:48
 */
@RestController
@RequestMapping("/rabbit")
@Api(tags = "rabbit接口")
@Slf4j
public class RabbitController {

    @Autowired
    private TestProducer testProducer;

    @Autowired
    private Test01Producer test01Producer;

    @Autowired
    private Test02Producer test02Producer;

    @Autowired
    private TestSendProduce testSendProduce;


    @PostMapping(value = "rabbitTest")
    public String rabbitTest(@RequestBody NumberReq numberReq) {
        Integer number = numberReq.getNumber();
        if (number == 1) {
            testProducer.sendOrderQueue();
            return "延时成功";
        } else if (number == 2) {
            test01Producer.sendOrderQueue();
            return "成功";
        } else if (number == 3) {
            test02Producer.sendOrderQueue();
            return "ttl延时成功";
        } else if (number == 4) {
            testSendProduce.sendOrderQueue();
            return "send延时成功";
        }
        return "";
    }
}
