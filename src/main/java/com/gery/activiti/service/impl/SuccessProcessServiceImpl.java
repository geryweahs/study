package com.gery.activiti.service.impl;

import org.activiti.engine.delegate.DelegateExecution;

public class SuccessProcessServiceImpl extends CommonBusinessService{

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Object data = delegateExecution.getVariable("data");
        System.out.println("这是成功方法"+data);
    }
}