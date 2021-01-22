package com.wsw.patrickstarsearchservice.repository;

import com.wsw.patrickstarsearchservice.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author WangSongWen
 * @Date: Created in 10:48 2021/1/22
 * @Description:
 */
@Repository
public interface ElasticRepository extends ElasticsearchRepository<Blog, String> {

}
