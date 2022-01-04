package com.wsw.patrickstar.task.feign.client;

import com.wsw.patrickstar.entity.Recepienter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author WangSongWen
 * @Date: Created in 15:00 2021/1/18
 * @Description: openFeign调用recepienter服务
 */
@FeignClient(value = "patrickstar-recepienter-service")
public interface RecepienterClient {
    @PostMapping("/recepienter/create")
    int create(@RequestBody Recepienter recepienter);
}
