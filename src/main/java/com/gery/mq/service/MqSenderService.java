package com.gery.mq.service;

import com.gery.mq.enums.QueueEnum;
import com.gery.mq.model.BaseMessage;
import com.gery.mq.model.dto.TestMessageDto;

/**
 * @Description: MqSenderService
 * @Author: YaoWenHua
 * @Date: 2022/11/18 10:40
 */
public interface MqSenderService {

    /***
     * @Description: sendMessage
     *
     * @Param: [exchange, routingKey, message]
     * @return: void
     * @Author: YaoWenHua
     * @Date: 2022/11/18
     */
    void sendMessage(String exchange, String routingKey, BaseMessage message);

    /***
     * @Description: 插件专用
     *
     * @Param: [exchange, routingKey, message, delay(延迟时间)]
     * @return: void
     * @Author: YaoWenHua
     * @Date: 2022/11/18
     */
    void sendMessage(String exchange, String routingKey, BaseMessage baseMessage, Integer delay);

    /***
     * @Description: sendMessage
     *
     * @Param: [queueEnum, messageDto]
     * @return: void
     * @Author: YaoWenHua
     * @Date: 2022/11/18
     */
    void sendMessage(QueueEnum queueEnum, TestMessageDto messageDto);

    /***
     * @Description: sendMessage
     *
     * @Param: [queueEnum, messageDto, delay]
     * @return: void
     * @Author: YaoWenHua
     * @Date: 2022/11/18
     */
    void sendMessage(QueueEnum queueEnum, TestMessageDto messageDto, Integer delay);
}
