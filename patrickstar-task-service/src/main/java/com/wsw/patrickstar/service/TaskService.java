package com.wsw.summercloud.service;

import com.wsw.summercloud.domain.Task;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 14:19 2020/11/9
 * @Description:
 */
public interface TaskService {
    int createTask(Task task);

    int updateTaskById(Task task);

    int updateTaskByName(Task task);

    int updateTaskStatusByTaskId(Long taskId, char taskStatus);

    int deleteTaskByTaskId(Long taskId);

    int deleteTaskByTaskName(String taskName);

    Task selectTaskById(Long taskId);

    List<Task> selectTaskByName(String taskName);

    List<Task> selectTaskByStatus(char taskStatus);

    // ...
}
