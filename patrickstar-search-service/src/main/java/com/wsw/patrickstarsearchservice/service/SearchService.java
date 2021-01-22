package com.wsw.patrickstarsearchservice.service;

import com.wsw.patrickstarsearchservice.entity.Blog;
import org.springframework.data.domain.Page;

/**
 * @Author WangSongWen
 * @Date: Created in 14:06 2021/1/22
 * @Description:
 */
public interface SearchService {
    Page<Blog> search(String keyWord, int page, int size);
}
