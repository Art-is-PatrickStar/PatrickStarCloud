package com.wsw.patrickstar.repository;

import com.wsw.patrickstar.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 17:33 2021/1/19
 * @Description:
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    int deleteTaskByTaskId(Long taskId);

    int deleteTaskByTaskName(String taskName);

    Task findTaskByTaskId(Long taskId);

    List<Task> findTaskByTaskName(String taskName);

    List<Task> findTaskByTaskStatus(char taskStatus);
}
