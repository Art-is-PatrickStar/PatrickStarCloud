package com.wsw.patrickstarsearchservice.service.impl;

import com.wsw.patrickstarsearchservice.entity.Blog;
import com.wsw.patrickstarsearchservice.repository.BlogRepository;
import com.wsw.patrickstarsearchservice.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 10:50 2021/1/22
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogRepository blogRepository;

    @Override
    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Optional<Blog> getBlogById(String id) {
        return blogRepository.findById(id);
    }

    @Override
    public Iterable<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    @Override
    public void deleteAllBlog() {
        blogRepository.deleteAll();
    }

    @Override
    public void deleteBlogById(String id) {
        blogRepository.deleteById(id);
    }
}
