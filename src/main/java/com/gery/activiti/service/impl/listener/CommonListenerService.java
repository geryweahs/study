package com.gery.activiti.service.impl.listener;

import com.gery.activiti.service.impl.BaseComponentService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommonListenerService extends BaseComponentService {

    protected void addFlowParameter(DelegateTask delegateTask, String key, Object value) {
        delegateTask.setVariable(key, value);
    }
}
