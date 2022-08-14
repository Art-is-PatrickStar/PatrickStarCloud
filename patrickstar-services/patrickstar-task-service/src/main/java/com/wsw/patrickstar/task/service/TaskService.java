package com.wsw.patrickstar.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.api.model.dto.TaskRequestDTO;
import com.wsw.patrickstar.common.base.PageInfo;
import com.wsw.patrickstar.task.entity.TaskEntity;

/**
 * @Author WangSongWen
 * @Date: Created in 14:19 2020/11/9
 * @Description:
 */
public interface TaskService extends IService<TaskEntity> {
    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);

    void deleteTask(Long taskId);

    PageInfo<TaskDTO> selectTask(TaskRequestDTO taskRequestDTO);

    TaskDTO selectTaskByTaskId(Long taskId);

    void hisResourceProcess(TaskRequestDTO taskRequestDTO);

    void taskBatchProcess(TaskRequestDTO taskRequestDTO);
}
