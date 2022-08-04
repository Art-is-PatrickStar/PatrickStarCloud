package com.wsw.patrickstar.recepienter.controller;

import com.wsw.patrickstar.api.model.dto.TaskRecordDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.recepienter.service.RecepienterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author WangSongWen
 * @Date: Created in 14:30 2020/11/20
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/recepienter")
@Api(tags = "任务流转相关接口")
public class RecepienterController {
    @Resource
    private RecepienterService recepienterService;

    @ApiOperation("创建任务记录")
    @PostMapping("/createTaskRecord")
    public Result<Void> createTaskRecord(@RequestBody @Valid TaskRecordDTO taskRecordDTO) {
        Result<Void> result = Result.createFailResult();
        try {
            recepienterService.createTaskRecord(taskRecordDTO);
            result = Result.createSuccessResult();
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.toString());
        }
        return result;
    }
}
