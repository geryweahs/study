package com.gery.activiti.service.impl;

import org.activiti.engine.delegate.DelegateExecution;

public class PrePreparationServiceImpl extends CommonBusinessService {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("这是数据准备方法");
        delegateExecution.setVariable("resultFlag", 1);
    }
}
