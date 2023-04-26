package com.gery.activiti.service.impl.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class NewExecutionListener extends CommonListenerService implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        String flowName = delegateExecution.getEventName();
        if (log.isInfoEnabled()) {
            log.info("{}节点的参数：{}", flowName, delegateExecution.getVariables());
        }
        String data = (String) delegateExecution.getVariable("data");
        Map map = JSON.parseObject(data, Map.class);
        map.put("key4", "value4");
        addFlowParameter(delegateExecution, "data", JSON.toJSONString(map));
    }


}
