package com.wsw.summercloud.controller;

import com.wsw.summercloud.api.CommonResult;
import com.wsw.summercloud.domain.Task;
import com.wsw.summercloud.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:03 2020/11/9
 * @Description: 前后端交互接口
 *
 * 1. Get请求
 *    1.1 xxx/task/select/{taskId}形式 -> @PathVariable("taskId")
 *    1.2 xxx/task/select?taskId=1形式 -> @RequestParam("taskId")
 * 2. Post请求
 *    前台请求参数设置 -> contentType: "application/json" -> xxx/task/create形式 -> @RequestBody
 */
@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("/create")
    public CommonResult createTask(@RequestBody Task task) {
        int result = taskService.createTask(task);
        if (result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @PutMapping("/update/byid")
    public CommonResult updateTaskById(@RequestBody Task task){
        int result = taskService.updateTaskById(task);
        if (result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @PutMapping("/update/byname")
    public CommonResult updateTaskByName(@RequestBody Task task){
        int result = taskService.updateTaskByName(task);
        if (result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @GetMapping("/updatestatus/byid")
    public CommonResult updateTaskStatusByTaskId(@RequestParam("taskId") Long taskId, @RequestParam("taskStatus") char taskStatus){
        int result = taskService.updateTaskStatusByTaskId(taskId, taskStatus);
        if (result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @DeleteMapping("/delete/byid")
    public CommonResult deleteTaskByTaskId(@RequestParam("taskId") Long taskId){
        int result = taskService.deleteTaskByTaskId(taskId);
        if (result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @DeleteMapping("/delete/byname")
    public CommonResult deleteTaskByTaskName(@RequestParam("taskName") String taskName){
        int result = taskService.deleteTaskByTaskName(taskName);
        if (result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @GetMapping("/select/byid/{taskId}")
    public CommonResult selectTaskById(@PathVariable("taskId") Long taskId){
        Task task = taskService.selectTaskById(taskId);
        if (null != task){
            return CommonResult.success(task);
        }
        return CommonResult.failed();
    }

    @GetMapping("/select/byname")
    public CommonResult selectTaskByName(@RequestParam("taskName") String taskName){
        List<Task> tasks = taskService.selectTaskByName(taskName);
        if (CollectionUtils.isNotEmpty(tasks)){
            return CommonResult.success(tasks);
        }
        return CommonResult.failed();
    }

    @GetMapping("/select/bystatus/{taskStatus}")
    public CommonResult selectTaskByStatus(@PathVariable("taskStatus") char taskStatus){
        List<Task> tasks = taskService.selectTaskByStatus(taskStatus);
        if (CollectionUtils.isNotEmpty(tasks)){
            return CommonResult.success(tasks);
        }
        return CommonResult.failed();
    }
}
