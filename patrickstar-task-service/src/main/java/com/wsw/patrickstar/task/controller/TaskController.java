package com.wsw.patrickstar.task.controller;

import com.wsw.patrickstar.api.Result;
import com.wsw.patrickstar.task.entity.Task;
import com.wsw.patrickstar.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:03 2020/11/9
 * @Description: 前后端交互接口
 * <p>
 * 1. Get请求
 * 1.1 xxx/task/select/{taskId}形式 -> @PathVariable("taskId")
 * 1.2 xxx/task/select?taskId=1形式 -> @RequestParam("taskId")
 * 2. Post请求
 * 前台请求参数设置 -> contentType: "application/json" -> xxx/task/create形式 -> @RequestBody
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("/create")
    @ResponseBody
    public Result<String> createTask(@RequestBody Task task) {
        Result<String> result = Result.createFailResult();
        int i = taskService.createTask(task);
        if (i > 0) {
            result = Result.createSuccessResult("创建任务成功!");
        }
        return result;
    }

    @PutMapping("/update/byid")
    @ResponseBody
    public Result<String> updateTaskById(@RequestBody Task task) {
        Result<String> result = Result.createFailResult();
        int i = taskService.updateTaskById(task);
        if (i > 0) {
            result = Result.createSuccessResult("更新任务成功!");
        }
        return result;
    }

    @PutMapping("/update/byname")
    @ResponseBody
    public Result<String> updateTaskByName(@RequestBody Task task) {
        Result<String> result = Result.createFailResult();
        int i = taskService.updateTaskByName(task);
        if (i > 0) {
            result = Result.createSuccessResult("更新任务成功!");
        }
        return result;
    }

    @PutMapping("/updatestatus/byid")
    @ResponseBody
    public Result<String> updateTaskStatusByTaskId(@RequestBody Task task) {
        Result<String> result = Result.createFailResult();
        int i = taskService.updateTaskStatusByTaskId(task);
        if (i > 0) {
            result = Result.createSuccessResult("更新任务成功!");
        }
        return result;
    }

    @DeleteMapping("/delete/byid")
    @ResponseBody
    public Result<String> deleteTaskByTaskId(@RequestParam("taskId") Long taskId) {
        Result<String> result = Result.createFailResult();
        int i = taskService.deleteTaskByTaskId(taskId);
        if (i > 0) {
            result = Result.createSuccessResult("删除任务成功!");
        }
        return result;
    }

    @DeleteMapping("/delete/byname")
    @ResponseBody
    public Result<String> deleteTaskByTaskName(@RequestParam("taskName") String taskName) {
        Result<String> result = Result.createFailResult();
        int i = taskService.deleteTaskByTaskName(taskName);
        if (i > 0) {
            result = Result.createSuccessResult("删除任务成功!");
        }
        return result;
    }

    @GetMapping("/select/byid/{taskId}")
    @ResponseBody
    public Result<Task> selectTaskById(@PathVariable("taskId") Long taskId) {
        Result<Task> result = Result.createFailResult();
        Task task = taskService.selectTaskById(taskId);
        result = Result.createSuccessResult(task);
        return result;
    }

    @GetMapping("/select/byid")
    @ResponseBody
    public Result<Task> selectTask(@RequestParam("taskId") Long taskId) {
        Result<Task> result = Result.createFailResult();
        Task task = taskService.selectTaskById(taskId);
        result = Result.createSuccessResult(task);
        return result;
    }

    @GetMapping("/select/byname")
    @ResponseBody
    public Result<List<Task>> selectTaskByName(@RequestParam("taskName") String taskName) {
        Result<List<Task>> result = Result.createFailResult();
        List<Task> tasks = taskService.selectTaskByName(taskName);
        result = Result.createSuccessResult(tasks);
        return result;
    }

    @GetMapping("/select/bystatus/{taskStatus}")
    @ResponseBody
    public Result<List<Task>> selectTaskByStatus(@PathVariable("taskStatus") char taskStatus) {
        Result<List<Task>> result = Result.createFailResult();
        List<Task> tasks = taskService.selectTaskByStatus(taskStatus);
        result = Result.createSuccessResult(tasks);
        return result;
    }
}
