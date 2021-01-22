package com.wsw.patrickstarsearchservice.service;

import com.wsw.patrickstarsearchservice.entity.Blog;

import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 10:50 2021/1/22
 * @Description:
 */
public interface BlogService {
    void addBlog(Blog blog);

    Optional<Blog> getBlogById(String id);

    Iterable<Blog> getAllBlog();

    void deleteAllBlog();

    void deleteBlogById(String id);
}
