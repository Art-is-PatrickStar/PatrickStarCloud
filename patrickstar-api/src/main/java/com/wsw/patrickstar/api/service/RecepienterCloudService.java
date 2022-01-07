package com.wsw.patrickstar.api.service;

import com.wsw.patrickstar.common.constant.CloudServiceNameConstants;
import com.wsw.patrickstar.api.domain.Recepienter;
import com.wsw.patrickstar.api.factory.RecepienterCloudServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: wangsongwen
 * @Date: 2022/1/6 16:56
 * @Description: Recepienter微服务接口
 */
@FeignClient(contextId = "recepienterCloudService", value = CloudServiceNameConstants.RECEPIENTER_SERVICE, fallbackFactory = RecepienterCloudServiceFallBackFactory.class)
public interface RecepienterCloudService {
    @PostMapping("/recepienter/create")
    int create(@RequestBody Recepienter recepienter);
}
