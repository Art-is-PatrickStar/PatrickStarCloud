package com.wsw.patrickstar.api.factory;

import com.wsw.patrickstar.api.model.dto.TaskRecordDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.service.RecepienterCloudService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: wangsongwen
 * @Date: 2022/1/6 16:59
 * @Description: Recepienter服务降级处理
 */
@Slf4j
@Component
public class RecepienterCloudServiceFallBackFactory implements FallbackFactory<RecepienterCloudService> {
    @Override
    public RecepienterCloudService create(Throwable throwable) {
        log.error("Recepienter服务调用失败: {}", throwable.getMessage());
        return new RecepienterCloudService() {
            @Override
            public Result<Void> createTaskRecord(TaskRecordDTO taskRecordDTO) {
                return Result.createFailResult();
            }
        };
    }
}
