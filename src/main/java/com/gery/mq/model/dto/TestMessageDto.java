package com.gery.mq.model.dto;

import com.gery.mq.model.BaseMessage;
import lombok.Data;

/**
 * @Description: TestMessageDto
 * @Author: YaoWenHua
 * @Date: 2022/11/18 13:39
 */
@Data
public class TestMessageDto extends BaseMessage {


    private String Status;


    private String relationId;
}
