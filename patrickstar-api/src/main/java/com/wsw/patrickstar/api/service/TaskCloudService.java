package com.wsw.patrickstar.api.service;

import com.wsw.patrickstar.api.factory.TaskCloudServiceFallBackFactory;
import com.wsw.patrickstar.api.model.constant.CloudServiceNameConstants;
import com.wsw.patrickstar.api.model.dto.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wangsongwen
 * @Date: 2022/1/6 16:27
 * @Description: Task微服务接口
 */
@FeignClient(contextId = "taskCloudService", value = CloudServiceNameConstants.TASK_SERVICE, fallbackFactory = TaskCloudServiceFallBackFactory.class)
public interface TaskCloudService {
    @GetMapping("/task/select/byid")
    TaskDTO selectTask(@RequestParam("taskId") Long taskId);
}
