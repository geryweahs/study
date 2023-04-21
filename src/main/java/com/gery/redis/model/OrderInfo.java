package com.gery.redis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: OrderInfo
 * @Author: YaoWenHua
 * @Date: 2022/11/9 15:42
 */
@Data
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 4694178753512978815L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    private String message;
}
