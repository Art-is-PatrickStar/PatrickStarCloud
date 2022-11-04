package com.wsw.patrickstar.search.controller;

import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.search.service.ElasticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:44 2021/1/22
 * @Description: ES查询接口
 */
@Slf4j
@RestController
@RequestMapping("/es")
public class ElasticController {
    @Resource
    private ElasticService elasticService;

    @GetMapping("/searchTask")
    public Result<List<TaskDTO>> searchTask(@RequestParam String keyWord) {
        Result<List<TaskDTO>> result = Result.createFailResult();
        try {
            List<TaskDTO> taskDTOS = elasticService.searchTask(keyWord);
            result = Result.createSuccessResult(taskDTOS);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage(), e.getCause());
        }
        return result;
    }

    @GetMapping("/getEsTaskById/{taskId}")
    public Result<TaskDTO> getEsTaskById(@PathVariable Long taskId) {
        Result<TaskDTO> result = Result.createFailResult();
        try {
            TaskDTO taskDTO = elasticService.getEsTaskById(taskId);
            result = Result.createSuccessResult(taskDTO);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage(), e.getCause());
        }
        return result;
    }

    @GetMapping("/getAllEsTask")
    public Result<List<TaskDTO>> getAllEsTask() {
        Result<List<TaskDTO>> result = Result.createFailResult();
        try {
            List<TaskDTO> taskDTOS = elasticService.getAllEsTask();
            result = Result.createSuccessResult(taskDTOS);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage(), e.getCause());
        }
        return result;
    }
}
