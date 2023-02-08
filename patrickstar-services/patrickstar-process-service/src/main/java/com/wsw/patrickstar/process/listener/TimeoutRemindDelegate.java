package com.wsw.patrickstar.process.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/2/8 16:29
 */
@Slf4j
@Component("timeoutRemindDelegate")
public class TimeoutRemindDelegate implements JavaDelegate {
    @Resource
    private TaskService taskService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("TimeoutRemindDelegate.execute start, execution: {}", delegateExecution);
        List<Task> taskList = taskService
                .createTaskQuery()
                .processInstanceId(delegateExecution.getProcessInstanceId())
                .list();
        for (Task task : taskList) {
            String assignee = task.getAssignee();
            log.info("assignee:" + assignee);
            // 实现保存记录表，发送语音提醒或短信提醒
        }
        log.info("TimeoutRemindDelegate.execute end");
    }
}