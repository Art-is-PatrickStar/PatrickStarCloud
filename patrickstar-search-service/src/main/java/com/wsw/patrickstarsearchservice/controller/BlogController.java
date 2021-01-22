package com.wsw.patrickstarsearchservice.controller;

import com.wsw.patrickstar.api.CommonResult;
import com.wsw.patrickstarsearchservice.entity.Blog;
import com.wsw.patrickstarsearchservice.service.BlogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 10:54 2021/1/22
 * @Description:
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody Blog blog) {
        try {
            blogService.addBlog(blog);
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
            Optional<Blog> blogOptional = blogService.getBlogById(id);
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
            Iterable<Blog> iterable = blogService.getAllBlog();
            List<Blog> list = new ArrayList<>();
            iterable.forEach(list::add);
            return CommonResult.success(list);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }
}
