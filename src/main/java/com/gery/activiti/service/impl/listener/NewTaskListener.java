package com.gery.activiti.service.impl.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class NewTaskListener extends CommonListenerService implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String flowName = delegateTask.getEventName();
        if (log.isInfoEnabled()) {
            log.info("{}节点的参数：{}", flowName, delegateTask.getVariables());
        }
        String data = (String) delegateTask.getVariable("data");
        Map map = JSON.parseObject(data, Map.class);
        map.put("key5", "value5");
        addFlowParameter(delegateTask, "data", JSON.toJSONString(map));
    }
}
