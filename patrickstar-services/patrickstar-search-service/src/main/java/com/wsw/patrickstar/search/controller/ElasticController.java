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
        Result<List<TaskDTO>> result = Result.createSuccessResult();
        List<TaskDTO> taskDTOS = elasticService.searchTask(keyWord);
        result.value(taskDTOS);
        return result;
    }

    @GetMapping("/getEsTaskById/{taskId}")
    public Result<TaskDTO> getEsTaskById(@PathVariable Long taskId) {
        Result<TaskDTO> result = Result.createSuccessResult();
        TaskDTO taskDTO = elasticService.getEsTaskById(taskId);
        result.value(taskDTO);
        return result;
    }

    @GetMapping("/getAllEsTask")
    public Result<List<TaskDTO>> getAllEsTask() {
        Result<List<TaskDTO>> result = Result.createSuccessResult();
        List<TaskDTO> taskDTOS = elasticService.getAllEsTask();
        result.value(taskDTOS);
        return result;
    }

    @PostMapping("/addEsTask")
    public Result<Void> addEsTask(@RequestBody List<TaskDTO> taskDTOS) {
        Result<Void> result = Result.createSuccessResult();
        elasticService.addEsTask(taskDTOS);
        return result;
    }

    @GetMapping("/deleteEsTaskById/{taskId}")
    public Result<TaskDTO> deleteEsTaskById(@PathVariable Long taskId) {
        Result<TaskDTO> result = Result.createSuccessResult();
        elasticService.deleteEsTaskById(taskId);
        return result;
    }

    @PostMapping("/updateEsTask")
    public Result<Void> updateEsTask(@RequestBody TaskDTO taskDTO) {
        Result<Void> result = Result.createSuccessResult();
        elasticService.updateEsTask(taskDTO);
        return result;
    }
}
