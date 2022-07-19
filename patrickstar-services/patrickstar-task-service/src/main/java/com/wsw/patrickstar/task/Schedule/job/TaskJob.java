package com.wsw.patrickstar.task.Schedule.job;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.task.entity.TaskEntity;
import com.wsw.patrickstar.task.mapper.TaskMapper;
import com.wsw.patrickstar.task.mapstruct.ITaskConvert;
import com.wsw.patrickstar.task.service.TaskService;
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
        TaskDTO taskDTO = null;
        long taskId;
        if (StringUtils.isNotBlank(param)) {
            taskId = Long.parseLong(param);
            taskDTO = taskService.selectTaskByTaskId(taskId);
        } else {
            Optional<TaskEntity> firstTask = new LambdaQueryChainWrapper<>(taskMapper)
                    .orderByDesc(TaskEntity::getUpdateTime)
                    .list()
                    .stream()
                    .findFirst();
            if (firstTask.isPresent()) {
                taskDTO = ITaskConvert.INSTANCE.entityToDto(firstTask.get());
            }
        }
        log.info("taskDTO = {}", taskDTO);
        XxlJobHelper.handleSuccess();
    }
}
