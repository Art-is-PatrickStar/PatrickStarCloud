package com.wsw.patrickstar.log.controller;

import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.log.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 14:21
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class OperationLogController {
    @Resource
    private OperationLogService operationLogService;

    @PostMapping("/getOperationLogs")
    public Result<List<OpLogDTO>> getOperationLogs(@RequestParam String moduleId) {
        return Result.createSuccessResult(operationLogService.getOperationLogs(moduleId));
    }
}
