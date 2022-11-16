package com.wsw.patrickstar.task.controller;

import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.api.model.dto.TaskRequestDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.response.ResultStatusEnums;
import com.wsw.patrickstar.common.annotation.OpLog;
import com.wsw.patrickstar.common.base.PageInfo;
import com.wsw.patrickstar.common.enums.ModuleTypeEnum;
import com.wsw.patrickstar.common.enums.OperationTypeEnum;
import com.wsw.patrickstar.common.exception.BusinessException;
import com.wsw.patrickstar.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

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
    @OpLog(opType = OperationTypeEnum.ADD, type = ModuleTypeEnum.TASK, typeId = "taskId", moduleId = "taskId")
    public Result<Void> createTask(@RequestBody @Valid TaskDTO taskDTO) {
        Result<Void> result = Result.createSuccessResult();
        taskService.createTask(taskDTO);
        return result;
    }

    @ApiOperation("更新任务")
    @PutMapping("/update")
    @OpLog(opType = OperationTypeEnum.UPDATE, type = ModuleTypeEnum.TASK, typeId = "taskId", moduleId = "taskId", serviceClass = TaskService.class, ignoreFields = {"createTime", "updateTime"})
    public Result<Void> updateTask(@RequestBody TaskDTO taskDTO) {
        Result<Void> result = Result.createSuccessResult();
        taskService.updateTask(taskDTO);
        return result;
    }

    @ApiOperation("删除任务")
    @DeleteMapping("/delete")
    @OpLog(opType = OperationTypeEnum.DELETE, type = ModuleTypeEnum.TASK, typeId = "taskId", moduleId = "taskId")
    public Result<Void> deleteTask(@RequestBody TaskDTO taskDTO) {
        Result<Void> result = Result.createSuccessResult();
        if (taskDTO.getTaskId() == null) {
            throw new BusinessException(ResultStatusEnums.VALIDATE_FAILED);
        }
        taskService.deleteTask(taskDTO.getTaskId());
        return result;
    }

    @ApiOperation("分页查询任务")
    @PostMapping("/select")
    public Result<PageInfo<TaskDTO>> selectTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        Result<PageInfo<TaskDTO>> result = Result.createSuccessResult();
        result.value(taskService.selectTask(taskRequestDTO));
        return result;
    }

    @ApiOperation("根据任务id查询任务")
    @GetMapping("/select/byTaskId")
    public Result<TaskDTO> selectTaskByTaskId(@RequestParam Long taskId) {
        Result<TaskDTO> result = Result.createSuccessResult();
        if (taskId == null) {
            throw new BusinessException(ResultStatusEnums.VALIDATE_FAILED);
        }
        result.value(taskService.selectTaskByTaskId(taskId));
        return result;
    }

    @ApiOperation("历史任务处理")
    @PostMapping("/hisResource/process")
    public Result<Void> hisResourceProcess(@RequestBody TaskRequestDTO taskRequestDTO) {
        Result<Void> result = Result.createSuccessResult();
        if (Objects.isNull(taskRequestDTO)) {
            throw new BusinessException(ResultStatusEnums.VALIDATE_FAILED);
        }
        taskService.hisResourceProcess(taskRequestDTO);
        return result;
    }

    @ApiOperation("批量处理任务")
    @PostMapping("/batch/process")
    public Result<Void> taskBatchProcess(@RequestBody TaskRequestDTO taskRequestDTO) {
        Result<Void> result = Result.createSuccessResult();
        if (Objects.isNull(taskRequestDTO)) {
            throw new BusinessException(ResultStatusEnums.VALIDATE_FAILED);
        }
        taskService.taskBatchProcess(taskRequestDTO);
        return result;
    }
}
