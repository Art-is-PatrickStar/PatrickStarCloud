package com.wsw.patrickstar.aconcurrent.service;

import com.wsw.patrickstar.aconcurrent.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author WangSongWen
 * @Date: Created in 14:50 2021/2/19
 * @Description:
 */
@Slf4j
@Service
public class TaskServiceImpl {
    private static final ThreadLocal<Log> taskLog = new ThreadLocal<>();

    public void excute() {
        try {
            taskLog.set(new Log().setId(0L).setName("任务流程"));
            logInfo("任务开始...");
            int i = 1 / 0;
            System.out.println(i);
        } catch (Exception e) {
            logError("任务执行异常: " + e.getMessage() + ", " + e.getCause());
        } finally {
            logInfo("任务结束");
            taskLog.remove();
        }

    }

    private void logInfo(String message) {
        try {
            Log logg = taskLog.get();
            if (logg == null) {
                return;
            }
            log.info(logg.nowRunTime().setMessage(message).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logError(String message) {
        try {
            Log logg = taskLog.get();
            if (logg == null) {
                return;
            }
            log.info(logg.nowRunTime().setMessage(message).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TaskServiceImpl().excute();
    }
}
