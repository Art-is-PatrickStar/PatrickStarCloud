package com.wsw.patrickstarsearchservice.service.impl;

import com.wsw.patrickstarsearchservice.entity.Blog;
import com.wsw.patrickstarsearchservice.service.SearchService;
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

/**
 * @Author WangSongWen
 * @Date: Created in 14:05 2021/1/22
 * @Description:
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    public Page<Blog> search(String keyWord, int page, int size) {
        if (page == 0 || size == 0) {
            page = 0;
            size = 10;
        }
        // 构建分页类
        Pageable pageable = PageRequest.of(page, size);
        // 构建查询NativeSearchQueryBuilder
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable);
        if (StringUtils.isNotBlank(keyWord)) {
            searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyWord));
        }
        SearchQuery searchQuery = searchQueryBuilder.build();
        Page<Blog> blogPage = elasticsearchTemplate.queryForPage(searchQuery, Blog.class);
        return blogPage;
    }

}
