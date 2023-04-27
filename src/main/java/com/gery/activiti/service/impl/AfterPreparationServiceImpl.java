package com.gery.activiti.service.impl;

import com.gery.activiti.model.domain.FlowEntity;
import org.activiti.engine.delegate.DelegateExecution;

public class AfterPreparationServiceImpl extends CommonBusinessService {
    @Override
    protected void doService(FlowEntity entity, DelegateExecution execution) {
        System.out.println("后置方法");

        execution.setVariable("resultFlag",1);
    }


}
