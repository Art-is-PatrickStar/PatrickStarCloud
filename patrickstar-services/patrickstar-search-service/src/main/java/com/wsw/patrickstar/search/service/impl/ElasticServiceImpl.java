package com.wsw.patrickstar.search.service.impl;

import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.search.entity.TaskEntity;
import com.wsw.patrickstar.search.mapstruct.ITaskConvert;
import com.wsw.patrickstar.search.repository.ElasticRepository;
import com.wsw.patrickstar.search.service.ElasticService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 15:45 2021/1/22
 * @Description: elasticsearch主服务
 */
@Service
public class ElasticServiceImpl implements ElasticService {
    @Resource
    private ElasticRepository elasticRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<TaskDTO> searchTask(String keyWord) {
        // 构建分页类
        Pageable pageable = PageRequest.of(0, 10);
        // 构建查询NativeSearchQueryBuilder
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable);
        if (StringUtils.isNotBlank(keyWord)) {
            searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyWord));
        }
        SearchQuery searchQuery = searchQueryBuilder.build();
        Page<TaskEntity> taskEntityPage = elasticsearchTemplate.queryForPage(searchQuery, TaskEntity.class);
        List<TaskEntity> taskEntities = taskEntityPage.getContent();
        List<TaskDTO> taskDTOS = ITaskConvert.INSTANCE.entitiesToDtos(taskEntities);
        return taskDTOS;
    }

    @Override
    public TaskDTO getEsTaskById(Long taskId) {
        Optional<TaskEntity> taskEntityOptional = elasticRepository.findById(taskId);
        TaskDTO taskDTO = new TaskDTO();
        if (taskEntityOptional.isPresent()) {
            taskDTO = ITaskConvert.INSTANCE.entityToDto(taskEntityOptional.get());
        }
        return taskDTO;
    }

    @Override
    public List<TaskDTO> getAllEsTask() {
        Iterable<TaskEntity> taskEntityIterable = elasticRepository.findAll();
        List<TaskEntity> taskEntities = new ArrayList<>();
        taskEntityIterable.forEach(taskEntities::add);
        List<TaskDTO> taskDTOS = ITaskConvert.INSTANCE.entitiesToDtos(taskEntities);
        return taskDTOS;
    }

    @Override
    public void addEsTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = ITaskConvert.INSTANCE.dtoToEntity(taskDTO);
        elasticRepository.save(taskEntity);
    }

    @Override
    public void deleteEsTaskById(Long taskId) {
        elasticRepository.deleteById(taskId);
    }

    @Override
    public void updateEsTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = ITaskConvert.INSTANCE.dtoToEntity(taskDTO);
        elasticRepository.save(taskEntity);
    }
}
