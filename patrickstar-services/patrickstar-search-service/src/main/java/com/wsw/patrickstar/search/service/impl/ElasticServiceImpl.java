package com.wsw.patrickstar.search.service.impl;

import com.wsw.patrickstar.api.domain.Task;
import com.wsw.patrickstar.search.repository.ElasticRepository;
import com.wsw.patrickstar.search.service.ElasticService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public Page<Task> search(String keyWord) {
        // 构建分页类
        Pageable pageable = PageRequest.of(0, 10);
        // 构建查询NativeSearchQueryBuilder
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable);
        if (StringUtils.isNotBlank(keyWord)) {
            searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyWord));
        }
        SearchQuery searchQuery = searchQueryBuilder.build();
        Page<Task> blogPage = elasticsearchTemplate.queryForPage(searchQuery, Task.class);
        return blogPage;
    }

    @Override
    public Optional<Task> getEsTaskById(Long taskId) {
        return elasticRepository.findById(taskId);
    }

    @Override
    public Iterable<Task> getAllEsTask() {
        return elasticRepository.findAll();
    }

    @Override
    public void addEsTask(Task task) {
        elasticRepository.save(task);
    }

    @Override
    public void deleteEsTaskById(Long taskId) {
        elasticRepository.deleteById(taskId);
    }

    @Override
    public void updateEsTask(Task task) {
        elasticRepository.save(task);
    }

}
