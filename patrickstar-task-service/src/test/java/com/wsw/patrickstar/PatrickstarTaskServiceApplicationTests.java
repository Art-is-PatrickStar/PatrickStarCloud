package com.wsw.patrickstar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.mapper.TaskMapper;
import com.wsw.patrickstar.service.impl.TaskServiceImpl;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class PatrickstarTaskServiceApplicationTests {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private TaskMapper taskMapper;

    public void selectTask(char taskStatus) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_status", taskStatus);
        List<Map<String, Object>> taskMapList = taskMapper.selectMaps(queryWrapper);
        for (Map<String, Object> taskMap : taskMapList) {
            String taskName = MapUtils.getString(taskMap, "task_name", "");
            System.out.println(taskName);
        }
    }

    @Test
    void contextLoads() {
        selectTask('0');
    }

}
