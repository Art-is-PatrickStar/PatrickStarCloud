package com.wsw.patrickstar.search.controller;

import com.wsw.patrickstar.api.Result;
import com.wsw.patrickstar.search.entity.Task;
import com.wsw.patrickstar.search.service.ElasticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 15:44 2021/1/22
 * @Description: ES查询接口
 */
@Slf4j
@RestController
@RequestMapping("/es/task")
public class ElasticController {
    @Resource
    private ElasticService elasticService;

    @GetMapping("/get/{taskId}")
    public Result<Task> getEsTaskById(@PathVariable Long taskId) {
        Result<Task> result = Result.createFailResult();
        try {
            Optional<Task> taskOptional = elasticService.getEsTaskById(taskId);
            Task task = null;
            if (taskOptional.isPresent()) {
                task = taskOptional.get();
            }
            result = Result.createSuccessResult(task);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage(), e.getCause());
        }
        return result;
    }

    @GetMapping("/get/all")
    public Result<List<Task>> getAllEsTask() {
        Result<List<Task>> result = Result.createFailResult();
        try {
            Iterable<Task> taskIterable = elasticService.getAllEsTask();
            List<Task> taskList = new ArrayList<>();
            taskIterable.forEach(taskList::add);
            result = Result.createSuccessResult(taskList);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage(), e.getCause());
        }
        return result;
    }

    @GetMapping("/search")
    public Result<List<Task>> search(@RequestParam String keyWord) {
        Result<List<Task>> result = Result.createFailResult();
        try {
            Page<Task> taskPage = elasticService.search(keyWord);
            List<Task> taskList = taskPage.getContent();
            result = Result.createSuccessResult(taskList);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage(), e.getCause());
        }
        return result;
    }
}
