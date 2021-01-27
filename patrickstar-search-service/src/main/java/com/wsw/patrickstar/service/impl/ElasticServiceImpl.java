package com.wsw.patrickstar.service.impl;

import com.wsw.patrickstar.entity.Blog;
import com.wsw.patrickstar.repository.ElasticRepository;
import com.wsw.patrickstar.service.ElasticService;
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

    public Page<Blog> search(String keyWord) {
        // 构建分页类
        Pageable pageable = PageRequest.of(0, 10);
        // 构建查询NativeSearchQueryBuilder
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable);
        if (StringUtils.isNotBlank(keyWord)) {
            searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyWord));
        }
        SearchQuery searchQuery = searchQueryBuilder.build();
        Page<Blog> blogPage = elasticsearchTemplate.queryForPage(searchQuery, Blog.class);
        return blogPage;
    }

    @Override
    public void addBlog(Blog blog) {
        elasticRepository.save(blog);
    }

    @Override
    public Optional<Blog> getBlogById(String id) {
        return elasticRepository.findById(id);
    }

    @Override
    public Iterable<Blog> getAllBlog() {
        return elasticRepository.findAll();
    }

    @Override
    public void deleteAllBlog() {
        elasticRepository.deleteAll();
    }

    @Override
    public void deleteBlogById(String id) {
        elasticRepository.deleteById(id);
    }
}
