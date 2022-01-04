package com.wsw.patrickstar.search.service;

import com.wsw.patrickstar.search.entity.Task;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 15:45 2021/1/22
 * @Description:
 */
public interface ElasticService {
    Page<Task> search(String keyWord);

    Optional<Task> getEsTaskById(Long taskId);

    Iterable<Task> getAllEsTask();

    void addEsTask(Task task);

    void deleteEsTaskById(Long taskId);

    void updateEsTask(Task task);
}
