package com.wsw.patrickstar.task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.task.entity.TaskOperationLog;
import com.wsw.patrickstar.task.mapper.TaskOperationLogMapper;
import com.wsw.patrickstar.task.mapstruct.ITaskOperationLogConvert;
import com.wsw.patrickstar.task.service.TaskOperationLogService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 10:57
 */
@Service
public class TaskOperationLogServiceImpl extends ServiceImpl<TaskOperationLogMapper, TaskOperationLog> implements TaskOperationLogService {
    @Override
    public void saveLog(OpLogDTO opLogDTO) {
        TaskOperationLog taskOperationLog = ITaskOperationLogConvert.INSTANCE.dtoToEntity(opLogDTO);
        this.save(taskOperationLog);
    }
}
