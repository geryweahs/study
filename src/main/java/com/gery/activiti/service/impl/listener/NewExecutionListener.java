package com.gery.activiti.service.impl.listener;

import com.alibaba.fastjson.JSON;
import com.gery.activiti.common.constant.ActivitiConstant;
import com.gery.activiti.model.domain.FlowEntity;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class NewExecutionListener extends CommonListenerService implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        String flowName = delegateExecution.getEventName();
        String data = (String) delegateExecution.getVariable(ActivitiConstant.DATA);
        if (log.isInfoEnabled()) {
            log.info("{}节点的参数：{}", flowName, data);
        }
        data = StringUtils.isBlank(data) ? getProcessVariables(runtimeService, delegateExecution.getProcessInstanceId()) : data;
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException("异常");
        }
        FlowEntity flowEntity = JSON.parseObject(data, FlowEntity.class);
        flowEntity.getListenerList().add(this.getClass().getCanonicalName().concat(":").concat(flowName));
        addFlowParameter(delegateExecution, ActivitiConstant.DATA, JSON.toJSONString(flowEntity));
    }


}
