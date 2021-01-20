package com.wsw.patrickstar.repository;

import com.wsw.patrickstar.entity.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 17:33 2021/1/19
 * @Description:
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    int deleteTaskByTaskId(@Param("taskId") Long taskId);

    int deleteTaskByTaskName(@Param("taskName") String taskName);

    Task findTaskByTaskId(@Param("taskId") Long taskId);

    List<Task> findTaskByTaskName(@Param("taskName") String taskName);

    List<Task> findTaskByTaskStatus(@Param("taskStatus") char taskStatus);
}
