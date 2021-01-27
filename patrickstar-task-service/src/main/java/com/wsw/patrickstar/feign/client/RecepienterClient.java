package com.wsw.patrickstar.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WangSongWen
 * @Date: Created in 15:00 2021/1/18
 * @Description: openFeign调用recepienter服务
 */
@FeignClient(value = "patrickstar-recepienter-service")
public interface RecepienterClient {
    @PostMapping("/recepienter/create")
    int create(@RequestParam("taskId") Long taskId, @RequestParam("taskName") String taskName, @RequestParam("name") String name, @RequestParam("remark") String remark);
}
