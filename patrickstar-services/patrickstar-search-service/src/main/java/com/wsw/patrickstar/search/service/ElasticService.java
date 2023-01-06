package com.wsw.patrickstar.search.service;

import com.wsw.patrickstar.api.model.dto.TaskDTO;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:45 2021/1/22
 * @Description:
 */
public interface ElasticService {
    List<TaskDTO> searchTask(String keyWord);

    TaskDTO getEsTaskById(Long taskId);

    List<TaskDTO> getAllEsTask();

    void addEsTask(List<TaskDTO> taskDTOS);

    void deleteEsTaskById(Long taskId);

    void updateEsTask(TaskDTO taskDTO);
}
