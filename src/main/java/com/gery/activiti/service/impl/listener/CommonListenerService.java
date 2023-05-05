package com.gery.activiti.service.impl.listener;

import com.gery.activiti.service.impl.BaseComponentService;
import org.activiti.engine.delegate.DelegateTask;

public abstract class CommonListenerService extends BaseComponentService {


    protected void addFlowParameter(DelegateTask delegateTask, String key, Object value) {
        delegateTask.setVariable(key, value);
    }
}
