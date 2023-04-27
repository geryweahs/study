package com.gery.activiti.service.impl.listener;

import com.gery.activiti.service.impl.BaseComponentService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;

public  class CommonListenerService extends BaseComponentService{

    @Autowired
    protected RuntimeService runtimeService;




}
