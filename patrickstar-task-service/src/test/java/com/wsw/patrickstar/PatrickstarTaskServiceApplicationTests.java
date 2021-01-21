package com.wsw.patrickstar;

import com.wsw.patrickstar.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PatrickstarTaskServiceApplicationTests {
    @Resource
    private TaskServiceImpl taskService;

    @Test
    void contextLoads() {

    }

}
