package com.gery.activiti.service.impl;

import com.gery.activiti.model.domain.FlowEntity;
import org.activiti.engine.delegate.DelegateExecution;


public class FailProcessServiceImpl extends CommonBusinessService {


    @Override
    protected void doService(FlowEntity entity, DelegateExecution execution) {
        System.out.println("失败方法");
    }


}
