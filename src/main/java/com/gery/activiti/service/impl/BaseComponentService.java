package com.gery.activiti.service.impl;

import com.gery.activiti.common.constant.ActivitiConstant;
import com.gery.common.utils.SpringContextUtil;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public abstract class BaseComponentService {
    protected RedisTemplate redisTemplate = null;



    public void init() {
        redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);
    }

    protected void addFlowParameter(DelegateExecution execution, String key, Object value) {
        execution.setVariable(key, value);
    }

    protected String getProcessVariables(String flowInstanceId) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(flowInstanceId)
                .includeProcessVariables()
                .singleResult();

        if (ObjectUtils.isNotEmpty(processInstance)) {
            Map<String, Object> processVariables = processInstance.getProcessVariables();
            return (String) processVariables.get(ActivitiConstant.DATA);
        }
        return null;
    }

    String getEventName(DelegateExecution execution) {
        BpmnModel bpmnModel = Context.getProcessEngineConfiguration()
                .getRepositoryService()
                .getBpmnModel(execution.getProcessDefinitionId());
        return bpmnModel.getFlowElement(execution.getCurrentActivityId()).getName();
    }
}
