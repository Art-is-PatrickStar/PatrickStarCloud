package com.wsw.patrickstar.aconcurrent.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author WangSongWen
 * @Date: Created in 9:27 2020/12/25
 * @Description:
 * corn表达式: 秒|分|时|日|月|周|年
 */
@Component
public class MySchedule {
    @Async("wswExecutor")
    //@Scheduled(cron = "0/1 * * * * ?")
    public void threadSchedule1() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("定时任务1开始: " + new Date());
        }
    }

//    @Async("executor2")
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void threadSchedule2() {
//        System.out.println("定时任务2开始: " + new Date());
//    }
}
