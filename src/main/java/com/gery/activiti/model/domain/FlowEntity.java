package com.gery.activiti.model.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel("流程实体")
@Data
public class FlowEntity {

    private String flowId;

    private String businessId;

    private String approvalId;


    private String flowInstanceId;

    private List<String> listenerList = new ArrayList<>();
}

