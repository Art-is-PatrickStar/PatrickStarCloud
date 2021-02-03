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
    public CommonResult createTask(@RequestBody Task task) {
        try {
            taskService.createTask(task);
            return CommonResult.success("创建任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("创建任务失败!");
        }
    }

    @PutMapping("/update/byid")
    public CommonResult updateTaskById(@RequestBody Task task) {
        try {
            taskService.updateTaskById(task);
            return CommonResult.success("更新任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("更新任务失败!");
        }
    }

    @PutMapping("/update/byname")
    public CommonResult updateTaskByName(@RequestBody Task task) {
        try {
            taskService.updateTaskByName(task);
            return CommonResult.success("更新任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("更新任务失败!");
        }
    }

    @PutMapping("/updatestatus/byid")
    public CommonResult updateTaskStatusByTaskId(@RequestBody Task task) {
        try {
            taskService.updateTaskStatusByTaskId(task);
            return CommonResult.success("更新任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("更新任务失败!");
        }
    }

    @DeleteMapping("/delete/byid")
    public CommonResult deleteTaskByTaskId(@RequestParam("taskId") Long taskId) {
        try {
            taskService.deleteTaskByTaskId(taskId);
            return CommonResult.success("删除任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("删除任务失败!");
        }
    }

    @DeleteMapping("/delete/byname")
    public CommonResult deleteTaskByTaskName(@RequestParam("taskName") String taskName) {
        try {
            taskService.deleteTaskByTaskName(taskName);
            return CommonResult.success("删除任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("删除任务失败!");
        }
    }

    @GetMapping("/select/byid/{taskId}")
    public CommonResult selectTaskById(@PathVariable("taskId") Long taskId) {
        try {
            Task task = taskService.selectTaskById(taskId);
            return CommonResult.success(task);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("查询任务失败!");
        }
    }

    @GetMapping("/select/byid")
    public Task selectTask(@RequestParam("taskId") Long taskId) {
        Task task = taskService.selectTaskById(taskId);
        return task;
    }

    @GetMapping("/select/byname")
    public CommonResult selectTaskByName(@RequestParam("taskName") String taskName) {
        try {
            List<Task> tasks = taskService.selectTaskByName(taskName);
            return CommonResult.success(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("查询任务失败!");
        }
    }

    @GetMapping("/select/bystatus/{taskStatus}")
    public CommonResult selectTaskByStatus(@PathVariable("taskStatus") char taskStatus) {
        try {
            List<Task> tasks = taskService.selectTaskByStatus(taskStatus);
            return CommonResult.success(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("查询任务失败!");
        }
    }
}
