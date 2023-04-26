package com.gery.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.delegate.DelegateExecution;

import java.util.Map;

public class PrePreparationServiceImpl extends CommonBusinessService {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        Object data = delegateExecution.getVariable("data");
        Map map = JSON.parseObject(data.toString(), Map.class);
        map.put("key3","value3");
        System.out.println("这是数据准备方法"+map);
        delegateExecution.setVariable(delegateExecution.getId(),JSON.toJSONString(map));

        delegateExecution.setVariable("resultFlag", 1);
        delegateExecution.setVariable("data", JSON.toJSONString(map));
    }
}
