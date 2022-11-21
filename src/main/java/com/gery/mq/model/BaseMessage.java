package com.gery.mq.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: MessageDto
 * @Author: YaoWenHua
 * @Date: 2022/11/18 11:30
 */
@Data
public class BaseMessage implements Serializable {

    private static final long serialVersionUID = 3179903546608742290L;
    @ApiModelProperty("发送时间")
    private Date sendMessageTime;
    @ApiModelProperty("消息唯一id")
    private String correlationDataId;

}
