package com.gery.activiti.controller;

import com.gery.activiti.model.req.ActivitiReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/activiti")
@Api(tags = "redis接口")
@Slf4j
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private TaskService taskService;

    /**
     * 判断指定流程是否部署： @param flowCode 流程代码 @return true，部署、false 未部署
     */
    public boolean isDeployment(String flowCode) {
        //查询已经部署的流程
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().latestVersion().orderByProcessDefinitionKey().asc();
        List<ProcessDefinition> list = processDefinitionQuery.list();
        long count = list.stream().filter(s -> s.getKey().equals(flowCode)).count();
        return count > 0;
    }

    @ApiOperation(value = "启动流程", tags = "工作流相关")
    @PostMapping(value = "/startFlow")
    public String startFlow() throws Exception {
        log.info("开始启动流程");
        Map<String, Object> vars = new HashMap<>();
        // 调用 startTestFlow 方法启动流程
        String startTestFlow = startTestFlow("leave", UUID.randomUUID().toString(), "测试", vars, null);
        log.info("流程启动成功，流程实例id为：{}", startTestFlow);
        return startTestFlow;
    }

    @ApiOperation(value = "部署工作流", tags = "工作流相关")
    @PostMapping(value = "/createActiviti")
    public void createActiviti(@RequestBody ActivitiReq activitiReq) {
        // 部署流程
        Deployment deployment = repositoryService.createDeployment()
                // 设置名称
                .name("请假流程")
                // 设置流程资源
                .addClasspathResource("processes/" + activitiReq.getName() + ".bpmn20.xml").deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }


    @ApiOperation(value = "部门审批", tags = "工作流相关")
    @PostMapping(value = "/approval")
    public void approval(@RequestBody ActivitiReq activitiReq) {
        String flowInstanceId = activitiReq.getFlowInstanceId();
        String taskAssignee = activitiReq.getTaskAssignee();
        // 查询指定流程实例、指定任务处理人的任务
        Task task = taskService.createTaskQuery().processInstanceId(flowInstanceId).taskAssignee(activitiReq.getTaskAssignee()).singleResult();
        // 设置流程变量，将审批标识设置为 1
        Map<String, Object> vars = new HashMap<>();
        vars.put("approvalFlag", 1);
        if (task == null) {
            log.error("act error: taskService.createTaskQuery().processInstanceId(flowInstanceId).taskAssignee(userId).singleResult() is null,processInstanceId is :{},userId is:{}", flowInstanceId, taskAssignee);
            throw new RuntimeException("completeCustomerTask task is null,processInstanceId is:" + flowInstanceId + " userId is:" + taskAssignee);
        }
        // 完成任务
        taskService.complete(task.getId(), vars);
    }

    public String startTestFlow(String flowCode, String businessKey, String title, Map<String, Object> vars, ActivitiEventListener listener) throws Exception {
        // 判断流程是否已部署
        if (!isDeployment(flowCode)) {
            throw new Exception("启动流程，该流程未部署！");
        }
        // 如果运行时数据为空，则抛出异常
        if (vars == null) {
            throw new Exception("启动流程，运行时数据缺失！");
        }
        // 设置流程标题
        if (StringUtils.isNotBlank(title)) {
            vars.put("title", title);
        }
        // 如果监听器不为空，则添加监听器
        if (null != listener) {
            runtimeService.addEventListener(listener);
        }
        // 启动流程
       // ProcessInstance procIns = runtimeService.startProcessInstanceByKey(flowCode, businessKey, vars);
       // ProcessInstance processInstance = runtimeService.startProcessInstanceById("leave:1:b3303073-d4f1-11ed-9655-508492a1dbfa");
      ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");
        return processInstance.getId();
    }

}
