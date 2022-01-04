package com.wsw.patrickstar.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsw.patrickstar.api.Result;
import com.wsw.patrickstar.task.entity.Task;
import com.wsw.patrickstar.entity.User;
import com.wsw.patrickstar.task.mapper.TaskMapper;
import com.wsw.patrickstar.task.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:03 2021/8/27
 * @Description: 同一个库中, 未分表的数据不需要单独配置, 只有分表需要sharding配置
 */
@RestController
public class DemoController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private TaskMapper taskMapper;

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

    @GetMapping("/select/bytime1")
    public Result<List<Task>> selectTaskByTime1(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        Result<List<Task>> result = Result.createFailResult();
        List<Task> tasks = taskMapper.selectTaskByTime1(startDate, endDate);
        result = Result.createSuccessResult(tasks);
        return result;
    }

    @GetMapping("/select/bytime2")
    public Result<List<Task>> selectTaskByTime2(@RequestParam("date") String date) throws Exception {
        Result<List<Task>> result = Result.createFailResult();
        Date createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        List<Task> tasks = taskMapper.selectTaskByTime2(createDate);
        result = Result.createSuccessResult(tasks);
        return result;
    }

    @GetMapping("/select/bytime3")
    public Result<List<Task>> selectTaskByTime3(@RequestParam("timeList") List<String> timeList) throws Exception {
        Result<List<Task>> result = Result.createFailResult();
        List<Date> timeList1 = new ArrayList<>();
        for (String time : timeList) {
            timeList1.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
        }
        List<Task> tasks = taskMapper.selectTaskByTime3(timeList1);
        result = Result.createSuccessResult(tasks);
        return result;
    }
}
