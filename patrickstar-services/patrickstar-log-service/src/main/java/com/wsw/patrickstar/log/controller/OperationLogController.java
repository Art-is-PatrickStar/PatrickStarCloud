package com.wsw.patrickstar.log.controller;

import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.log.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/saveLog")
    public Result<Void> saveLog(@RequestBody OpLogDTO opLogDTO) {
        operationLogService.saveLog(opLogDTO);
        return Result.createSuccessResult();
    }

    @PostMapping("/getOperationLogs")
    public Result<List<OpLogDTO>> getOperationLogs(@RequestParam String moduleId) {
        return Result.createSuccessResult(operationLogService.getOperationLogs(moduleId));
    }
}
