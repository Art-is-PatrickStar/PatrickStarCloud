package com.wsw.patrickstar.mapper;

import com.wsw.patrickstar.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
