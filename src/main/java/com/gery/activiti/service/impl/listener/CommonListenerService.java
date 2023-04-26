package com.gery.activiti.service.impl.listener;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonListenerService {

    @Autowired
    protected RuntimeService runtimeService;


    protected void addFlowParameter(DelegateExecution execution, String key, Object value) {
        execution.setVariable(key, value);
    }

    protected void addFlowParameter(DelegateTask delegateTask, String key, Object value) {
        delegateTask.setVariable(key, value);
    }
}
