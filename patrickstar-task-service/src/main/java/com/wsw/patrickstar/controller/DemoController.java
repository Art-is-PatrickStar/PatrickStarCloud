package com.wsw.patrickstar.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsw.patrickstar.api.Result;
import com.wsw.patrickstar.entity.User;
import com.wsw.patrickstar.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 15:03 2021/8/27
 * @Description: 同一个库中, 未分表的数据不需要单独配置, 只有分表需要sharding配置
 */
@RestController
public class DemoController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/select/user/byname")
    @ResponseBody
    public Result<User> selectUserByName(@RequestParam("username") String username) {
        Result<User> result = Result.createFailResult();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        result = Result.createSuccessResult(user);
        return result;
    }
}
