package com.wsw.patrickstar.api.factory;

import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.service.OperationLogCloudService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 15:27
 */
@Slf4j
@Component
public class OperationLogCloudServiceFallBackFactory implements FallbackFactory<OperationLogCloudService> {
    @Override
    public OperationLogCloudService create(Throwable throwable) {
        log.error("Log服务调用失败: {}", throwable.getMessage());
        return new OperationLogCloudService() {
            @Override
            public Result<Void> saveLog(OpLogDTO opLogDTO) {
                return Result.createFailResult();
            }
        };
    }
}
