package com.wsw.patrickstar.task.service;

import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.task.entity.TaskOperationLog;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 10:55
 */
public interface TaskOperationLogService {
    void saveLog(OpLogDTO opLogDTO);
}
