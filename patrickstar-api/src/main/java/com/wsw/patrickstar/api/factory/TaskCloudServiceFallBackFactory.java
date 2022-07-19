package com.wsw.patrickstar.api.factory;

import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.service.TaskCloudService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: wangsongwen
 * @Date: 2022/1/6 16:49
 * @Description: Task服务降级处理
 */
@Slf4j
@Component
public class TaskCloudServiceFallBackFactory implements FallbackFactory<TaskCloudService> {
    @Override
    public TaskCloudService create(Throwable throwable) {
        log.error("Task服务调用失败: {}", throwable.getMessage());
        return new TaskCloudService() {
            @Override
            public Result<TaskDTO> selectTaskByTaskId(Long taskId) {
                return Result.createFailResult();
            }
        };
    }
}
