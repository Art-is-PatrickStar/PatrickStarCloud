package com.wsw.patrickstar.process.controller;

import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.process.service.FlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/2/8 16:29
 */
@Slf4j
@RestController
public class FlowController {
    @Resource
    private FlowService flowService;

    @PostMapping("/deployProcess")
    public Result<Void> deployProcess(@RequestParam("name") String name, @RequestParam("resource") String resource) {
        Result<Void> result = Result.createSuccessResult();
        flowService.deployProcess(name, resource);
        return result;
    }

    @GetMapping("/startProcess")
    public Result<Void> startProcess(@RequestParam("processKey") String processKey, @RequestParam("businessKey") String businessKey) {
        Result<Void> result = Result.createSuccessResult();
        flowService.startProcess(processKey, businessKey);
        return result;
    }
}
