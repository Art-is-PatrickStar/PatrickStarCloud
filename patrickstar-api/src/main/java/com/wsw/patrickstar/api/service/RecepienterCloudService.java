package com.wsw.patrickstar.api.service;

import com.wsw.patrickstar.api.factory.RecepienterCloudServiceFallBackFactory;
import com.wsw.patrickstar.api.model.constant.CloudServiceNameConstants;
import com.wsw.patrickstar.api.model.dto.TaskRecordDTO;
import com.wsw.patrickstar.api.response.Result;
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
    @PostMapping("/recepienter/createTaskRecord")
    Result<Void> createTaskRecord(@RequestBody TaskRecordDTO taskRecordDTO);
}
