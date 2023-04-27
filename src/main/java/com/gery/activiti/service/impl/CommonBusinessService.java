package com.gery.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.gery.activiti.common.constant.ActivitiConstant;
import com.gery.activiti.model.domain.FlowEntity;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public abstract class CommonBusinessService extends BaseComponentService implements JavaDelegate {

    private static Logger logger = LoggerFactory.getLogger(CommonBusinessService.class);

    protected abstract void doService(FlowEntity entity, DelegateExecution execution);

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) {
        String processInstanceId = execution.getProcessInstanceId();
        String eventName = getEventName(execution);
        String data = (String) execution.getVariable(ActivitiConstant.DATA);
        if (StringUtils.isEmpty(data)) {
            data = getProcessVariables(runtimeService, processInstanceId);
        }
        if (logger.isInfoEnabled()) {
            logger.info(eventName + "--组件流程参数: " + data);
        }
        FlowEntity flowEntity = JSON.parseObject(data, FlowEntity.class);
        flowEntity.setFlowInstanceId(processInstanceId);
        doService(flowEntity, execution);
        addFlowParameter(execution, ActivitiConstant.DATA, JSON.toJSONString(flowEntity));
    }
}
