package com.wsw.patrickstar.controller;

import com.wsw.patrickstar.api.CommonResult;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.service.ElasticService;
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
@RestController
@RequestMapping("/es/task")
public class ElasticController {
    @Resource
    private ElasticService elasticService;

    @GetMapping("/get/{taskId}")
    public CommonResult getEsTaskById(@PathVariable Long taskId) {
        try {
            Optional<Task> taskOptional = elasticService.getEsTaskById(taskId);
            Task task = null;
            if (taskOptional.isPresent()) {
                task = taskOptional.get();
            }
            return CommonResult.success(task);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    public CommonResult getAllEsTask() {
        try {
            Iterable<Task> taskIterable = elasticService.getAllEsTask();
            List<Task> taskList = new ArrayList<>();
            taskIterable.forEach(taskList::add);
            return CommonResult.success(taskList);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    @GetMapping("/search")
    public CommonResult search(@RequestParam String keyWord) {
        try {
            Page<Task> taskPage = elasticService.search(keyWord);
            List<Task> taskList = taskPage.getContent();
            return CommonResult.success(taskList);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }
}
