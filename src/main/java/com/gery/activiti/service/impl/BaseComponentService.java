package com.gery.activiti.service.impl;

import com.gery.activiti.common.constant.ActivitiConstant;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;

public abstract class BaseComponentService {


    protected void addFlowParameter(DelegateExecution execution, String key, Object value) {
        execution.setVariable(key, value);
    }

    protected void addFlowParameter(DelegateTask delegateTask, String key, Object value) {
        delegateTask.setVariable(key, value);
    }

    protected String getProcessVariables(RuntimeService runtimeService, String flowInstanceId) {
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
