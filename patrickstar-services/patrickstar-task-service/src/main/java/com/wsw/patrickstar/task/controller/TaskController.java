package com.wsw.patrickstar.task.controller;

import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.api.model.dto.TaskRequestDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.response.ResultStatusEnums;
import com.wsw.patrickstar.common.annotation.OpLog;
import com.wsw.patrickstar.common.base.PageInfo;
import com.wsw.patrickstar.common.enums.ModuleTypeEnum;
import com.wsw.patrickstar.common.enums.OperationType;
import com.wsw.patrickstar.task.service.TaskService;
import com.wsw.patrickstar.task.service.impl.TaskServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
@Api(tags = "任务操作相关接口")
public class TaskController {
    @Resource
    private TaskService taskService;

    @ApiOperation("创建任务")
    @PostMapping("/create")
    public Result<Void> createTask(@RequestBody @Valid TaskDTO taskDTO) {
        Result<Void> result = Result.createFailResult();
        try {
            taskService.createTask(taskDTO);
            result = Result.createSuccessResult();
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error("" + e);
        }
        return result;
    }

    @ApiOperation("更新任务")
    @PutMapping("/update")
    @OpLog(opType = OperationType.UPDATE, type = ModuleTypeEnum.TASK, typeId = "taskId", serviceClass = TaskService.class, ignoreFields = {"createTime", "updateTime"})
    public Result<Void> updateTask(@RequestBody TaskDTO taskDTO) {
        Result<Void> result = Result.createFailResult();
        try {
            taskService.updateTask(taskDTO);
            result = Result.createSuccessResult();
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("删除任务")
    @DeleteMapping("/delete")
    public Result<Void> deleteTaskByTaskId(@RequestParam("taskId") Long taskId) {
        Result<Void> result = Result.createFailResult();
        try {
            taskService.deleteTaskByTaskId(taskId);
            result = Result.createSuccessResult();
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("分页查询任务")
    @PostMapping("/select")
    public Result<PageInfo<TaskDTO>> selectTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        Result<PageInfo<TaskDTO>> result = Result.createFailResult();
        try {
            result = Result.createSuccessResult(taskService.selectTask(taskRequestDTO));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("根据任务id查询任务")
    @GetMapping("/select/byTaskId")
    public Result<TaskDTO> selectTaskByTaskId(@RequestParam Long taskId) {
        Result<TaskDTO> result = Result.createFailResult();
        if (taskId == null) {
            return Result.createFailResult(ResultStatusEnums.VALIDATE_FAILED);
        }
        try {
            result = Result.createSuccessResult(taskService.selectTaskByTaskId(taskId));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }
}
