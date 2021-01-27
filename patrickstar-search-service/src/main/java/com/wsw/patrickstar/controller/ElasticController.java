package com.wsw.patrickstar.controller;

import com.wsw.patrickstar.api.CommonResult;
import com.wsw.patrickstar.entity.Blog;
import com.wsw.patrickstar.service.ElasticService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 15:44 2021/1/22
 * @Description:
 */
@RestController
@RequestMapping("/blog")
public class ElasticController {
    @Resource
    private ElasticService elasticService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody Blog blog) {
        try {
            elasticService.addBlog(blog);
            return CommonResult.success(blog);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getBlogById(@PathVariable String id) {
        if (StringUtils.isBlank(id))
            return CommonResult.failed("id is empty");
        try {
            Optional<Blog> blogOptional = elasticService.getBlogById(id);
            Blog blog = null;
            if (blogOptional.isPresent()) {
                blog = blogOptional.get();
            }
            return CommonResult.success(blog);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    public CommonResult getAllBlog() {
        try {
            Iterable<Blog> iterable = elasticService.getAllBlog();
            List<Blog> list = new ArrayList<>();
            iterable.forEach(list::add);
            return CommonResult.success(list);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    @GetMapping("/search")
    public CommonResult search(@RequestParam String keyWord) {
        try {
            Page<Blog> blogPage = elasticService.search(keyWord);
            List<Blog> blogs = blogPage.getContent();
            return CommonResult.success(blogs);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }
}
