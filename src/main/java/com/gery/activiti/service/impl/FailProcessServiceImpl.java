package com.gery.activiti.service.impl;

import org.activiti.engine.delegate.DelegateExecution;

public class FailProcessServiceImpl extends  CommonBusinessService{

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("这是失败方法");
    }
}
