package com.gery.activiti.service.impl;

import org.activiti.engine.delegate.DelegateExecution;

public class SuccessProcessServiceImpl extends CommonBusinessService{

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("这是成功方法");
    }
}
