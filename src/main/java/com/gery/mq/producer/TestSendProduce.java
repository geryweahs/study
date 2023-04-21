package com.gery.mq.producer;

import com.alibaba.fastjson.JSON;
import com.gery.mq.enums.QueueEnum;
import com.gery.mq.model.dto.TestMessageDto;
import com.gery.mq.service.MqSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: TestSendProduce
 * @Author: YaoWenHua
 * @Date: 2022/11/18 10:57
 */
@Slf4j
@Component
public class TestSendProduce {


    @Autowired
    private MqSenderService mqSenderService;

    public void sendOrderQueue() {
        TestMessageDto messageDto = new TestMessageDto();
        messageDto.setStatus("1");
        messageDto.setRelationId("C101");
        log.info("发送插件延迟信息开始");
        try {
            //发送 普通消息
            mqSenderService.sendMessage(QueueEnum.QUEUE_TEST_01, messageDto);
            mqSenderService.sendMessage(
                    QueueEnum.QUEUE_TEST_01.getExchange(),
                    QueueEnum.QUEUE_TEST_01.getRouteKey(), messageDto);


            //发送 插件消息
//            mqSenderService.sendMessage(QueueEnum.QUEUE_TEST, messageDto, 20000);
//            mqSenderService.sendMessage(QueueEnum.QUEUE_TEST.getExchange(),
//                    QueueEnum.QUEUE_TEST.getRouteKey(), messageDto, 20000);


//            //发送 ttl 延迟消息
//            mqSenderService.sendMessage(QueueEnum.QUEUE_TEST_02.getExchange(),
//                    QueueEnum.QUEUE_TEST_02.getRouteKey(), messageDto);
//            mqSenderService.sendMessage(QueueEnum.QUEUE_TEST_02, messageDto);
        } catch (Throwable e) {
            log.error("推送订单{}事件出现异常：", JSON.toJSONString(messageDto), e);
        }
    }

}
