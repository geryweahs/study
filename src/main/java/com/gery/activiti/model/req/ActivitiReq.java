package com.gery.activiti.model.req;

import lombok.Data;

@Data
public class ActivitiReq {

    private String name;

    private String flowInstanceId;

    private String taskAssignee;


    private Integer approvalFlag;
}
