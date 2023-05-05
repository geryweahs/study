package com.gery.activiti.service.impl;

import com.gery.activiti.common.constant.ActivitiConstant;
import com.gery.activiti.model.domain.FlowEntity;
import com.gery.mq.service.MqSenderService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AfterPreparationServiceImpl extends CommonBusinessService {

    @Autowired
    private MqSenderService mqSenderService;


    @Override
    protected void doService(FlowEntity entity, DelegateExecution execution) {
        System.out.println("后置方法");
        redisTemplate.opsForValue().set(ActivitiConstant.KEY, entity.getApprovalId());
        execution.setVariable("resultFlag", 1);


    }
}