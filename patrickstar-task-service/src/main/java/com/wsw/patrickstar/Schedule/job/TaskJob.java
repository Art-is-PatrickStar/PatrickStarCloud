package com.wsw.patrickstar.Schedule.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.mapper.TaskMapper;
import com.wsw.patrickstar.service.TaskService;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author: wangsongwen
 * @Date: 2021/9/30 16:44
 * @Description: TaskJob执行
 */
@Slf4j
@Component
public class TaskJob {
    @Resource
    private TaskService taskService;
    @Resource
    private TaskMapper taskMapper;

    public void execute(String param) throws Exception {
        Task task = null;
        long taskId;
        if (StringUtils.isNotBlank(param)) {
            taskId = Long.parseLong(param);
            task = taskService.selectTaskById(taskId);
        } else {
            Optional<Task> firstTask = new LambdaQueryChainWrapper<>(taskMapper)
                    .orderByDesc(Task::getModifyDate)
                    .list()
                    .stream()
                    .findFirst();
            /*LambdaQueryWrapper<Task> lqw = new LambdaQueryWrapper<>();
            lqw.orderByDesc(Task::getModifyDate);
            Optional<Task> firstTask = taskMapper.selectList(lqw).stream().findFirst();*/
            if (firstTask.isPresent()) {
                task = firstTask.get();
            }
        }
        log.info("task = {}", task);
        XxlJobHelper.handleSuccess();
    }
}
