package com.wsw.patrickstar.search.feign.client;

import com.wsw.patrickstar.search.entity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WangSongWen
 * @Date: Created in 15:00 2021/1/18
 * @Description: openFeign调用task服务
 */
@FeignClient(value = "patrickstar-task-service")
public interface TaskClient {
    @GetMapping("/task/select/byid")
    Task selectTask(@RequestParam("taskId") Long taskId);
}
