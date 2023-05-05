package com.gery.activiti.service.impl.listener;

import com.alibaba.fastjson.JSON;
import com.gery.activiti.common.constant.ActivitiConstant;
import com.gery.activiti.model.domain.FlowEntity;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class NewTaskListener extends CommonListenerService implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        init();
        delegateTask.setAssignee((String) redisTemplate.opsForValue().get(ActivitiConstant.KEY));
        String data = (String) delegateTask.getVariable(ActivitiConstant.DATA);
        String flowName = delegateTask.getEventName();
        if (log.isInfoEnabled()) {
            log.info("{}节点的参数：{}", flowName, data);
        }
        FlowEntity flowEntity = JSON.parseObject(data, FlowEntity.class);
        flowEntity.getListenerList().add(flowName);
        addFlowParameter(delegateTask, ActivitiConstant.DATA, JSON.toJSONString(flowEntity));
    }
}
