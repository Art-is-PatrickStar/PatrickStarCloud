package com.wsw.patrickstar.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date: Created in 16:56 2021/2/19
 * @Description: 本地缓存
 */
@Service
public class LocalCacheServiceImpl {
    @Resource
    private JdbcTemplate jdbcTemplate;

    private final LoadingCache<String, List<Map<String, Object>>> cacheConfig = CacheBuilder
            .newBuilder()
            .expireAfterWrite(17, TimeUnit.MINUTES)
            .refreshAfterWrite(15, TimeUnit.MINUTES)
            .initialCapacity(3).maximumSize(5).build(new CacheLoader<String, List<Map<String, Object>>>() {
                @Override
                public List<Map<String, Object>> load(String configKey) {
                    String sql = "select * from task";
                    List<Map<String, Object>> taskMapList = jdbcTemplate.queryForList(sql);
                    if (CollectionUtils.isEmpty(taskMapList)) {
                        return null;
                    }
                    return taskMapList;
                }
            });

    public List<Map<String, Object>> excute() {
        List<Map<String, Object>> taskMapList = null;
        try {
            taskMapList = cacheConfig.get("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskMapList;
    }
}
