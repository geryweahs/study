package com.gery.activiti.service.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service
public class CallBackServiceImpl extends CommonBusinessService {


    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("这是回调方法");
    }
}
