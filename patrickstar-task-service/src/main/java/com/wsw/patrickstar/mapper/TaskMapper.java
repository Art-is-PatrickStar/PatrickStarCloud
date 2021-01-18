package com.wsw.summercloud.mapper;

import com.wsw.summercloud.domain.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 14:29 2020/11/9
 * @Description:
 */
@Mapper
public interface TaskMapper {
    int createTask(Task task);

    int updateTaskById(Task task);

    int updateTaskByName(Task task);

    int updateTaskStatusByTaskId(@Param("taskId") Long taskId, @Param("taskStatus") char taskStatus);

    int deleteTaskByTaskId(@Param("taskId") Long taskId);

    int deleteTaskByTaskName(@Param("taskName") String taskName);

    Task selectTaskById(@Param("taskId") Long taskId);

    List<Task> selectTaskByName(@Param("taskName") String taskName);

    List<Task> selectTaskByStatus(@Param("taskStatus") char taskStatus);
}
