package com.wsw.patrickstarsearchservice.controller;

import com.wsw.patrickstar.api.CommonResult;
import com.wsw.patrickstarsearchservice.entity.Blog;
import com.wsw.patrickstarsearchservice.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2021/1/22
 * @Description:
 */
@RestController
@RequestMapping("/blog")
public class SearchController {
    @Resource
    private SearchService searchService;

    @GetMapping("/search")
    public CommonResult search(@RequestParam String keyWord, @RequestParam int page, @RequestParam int size) {
        try {
            Page<Blog> blogs = searchService.search(keyWord, page, size);
            return CommonResult.success(blogs);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }
}
