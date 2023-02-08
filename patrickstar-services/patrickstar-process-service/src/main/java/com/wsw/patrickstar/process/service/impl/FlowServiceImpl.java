package com.wsw.patrickstar.process.service.impl;

import com.wsw.patrickstar.process.service.FlowService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/2/8 16:55
 */
@Slf4j
@Service
public class FlowServiceImpl implements FlowService {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private RepositoryService repositoryService;

    @Override
    public void deployProcess(String name, String resource) {
        Deployment deploy = repositoryService.createDeployment()
                .name(name)
                .addClasspathResource(resource)
                .deploy();
        log.info("流程id:" + deploy.getId());
        log.info("流程name:" + deploy.getName());
    }

    @Override
    public void startProcess(String processKey, String businessKey) {
        ProcessInstance timerSendMsg = runtimeService.startProcessInstanceByKey(processKey, businessKey);
        log.info("实例id: " + timerSendMsg.getId());
    }
}
