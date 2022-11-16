package com.wsw.patrickstar.api.service;

import com.wsw.patrickstar.api.factory.OperationLogCloudServiceFallBackFactory;
import com.wsw.patrickstar.api.model.constant.CloudServiceNameConstants;
import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.api.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 15:26
 */
@FeignClient(contextId = "operationLogCloudService", value = CloudServiceNameConstants.LOG_SERVICE, fallbackFactory = OperationLogCloudServiceFallBackFactory.class)
public interface OperationLogCloudService {
    @PostMapping("/log/saveLog")
    Result<Void> saveLog(@RequestBody OpLogDTO opLogDTO);
}
