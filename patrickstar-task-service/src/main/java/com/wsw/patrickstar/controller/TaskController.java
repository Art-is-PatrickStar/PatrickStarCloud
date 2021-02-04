package com.wsw.patrickstar.controller;

import com.wsw.patrickstar.api.CommonResult;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.service.TaskService;
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
@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("/create")
    @ResponseBody
    public CommonResult createTask(@RequestBody Task task) {
        taskService.createTask(task);
        return CommonResult.success("创建任务成功!");
    }

    @PutMapping("/update/byid")
    @ResponseBody
    public CommonResult updateTaskById(@RequestBody Task task) {
        taskService.updateTaskById(task);
        return CommonResult.success("更新任务成功!");
    }

    @PutMapping("/update/byname")
    @ResponseBody
    public CommonResult updateTaskByName(@RequestBody Task task) {
        taskService.updateTaskByName(task);
        return CommonResult.success("更新任务成功!");
    }

    @PutMapping("/updatestatus/byid")
    @ResponseBody
    public CommonResult updateTaskStatusByTaskId(@RequestBody Task task) {
        taskService.updateTaskStatusByTaskId(task);
        return CommonResult.success("更新任务成功!");
    }

    @DeleteMapping("/delete/byid")
    @ResponseBody
    public CommonResult deleteTaskByTaskId(@RequestParam("taskId") Long taskId) {
        taskService.deleteTaskByTaskId(taskId);
        return CommonResult.success("删除任务成功!");
    }

    @DeleteMapping("/delete/byname")
    @ResponseBody
    public CommonResult deleteTaskByTaskName(@RequestParam("taskName") String taskName) {
        taskService.deleteTaskByTaskName(taskName);
        return CommonResult.success("删除任务成功!");
    }

    @GetMapping("/select/byid/{taskId}")
    @ResponseBody
    public CommonResult selectTaskById(@PathVariable("taskId") Long taskId) {
        Task task = taskService.selectTaskById(taskId);
        return CommonResult.success(task);
    }

    @GetMapping("/select/byid")
    @ResponseBody
    public Task selectTask(@RequestParam("taskId") Long taskId) {
        Task task = taskService.selectTaskById(taskId);
        return task;
    }

    @GetMapping("/select/byname")
    @ResponseBody
    public CommonResult selectTaskByName(@RequestParam("taskName") String taskName) {
        List<Task> tasks = taskService.selectTaskByName(taskName);
        return CommonResult.success(tasks);
    }

    @GetMapping("/select/bystatus/{taskStatus}")
    @ResponseBody
    public CommonResult selectTaskByStatus(@PathVariable("taskStatus") char taskStatus) {
        List<Task> tasks = taskService.selectTaskByStatus(taskStatus);
        return CommonResult.success(tasks);
    }
}
