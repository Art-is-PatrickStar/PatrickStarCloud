package com.wsw.patrickstar.process.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/2/8 16:29
 */
@Slf4j
@RestController
public class FlowController {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private RepositoryService repositoryService;

    @PostMapping("/deployProcess")
    public void deployProcess(@RequestParam("name") String name, @RequestParam("resource") String resource) {
        Deployment deploy = repositoryService.createDeployment()
                .name(name)
                .addClasspathResource(resource)
                .deploy();
        log.info("流程Id:" + deploy.getId());
        log.info("流程Name:" + deploy.getName());
    }

    @GetMapping("/startProcess")
    public void startProcess() {
        ProcessInstance timerSendMsg = runtimeService.startProcessInstanceByKey("timerSendMsg2");
        log.info("instanceID: " + timerSendMsg.getId());
    }
}
