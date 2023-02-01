package com.wsw.patrickstar.aconcurrent.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author WangSongWen
 * @Date: Created in 10:07 2020/12/29
 * @Description:
 */
@Slf4j
public class TaskLogger {
    private static final ThreadLocal<Log> LOG_THREAD_LOCAL = new ThreadLocal<>();

    public static Log getLog() {
        return LOG_THREAD_LOCAL.get();
    }

    public static void setLog(Log log) {
        LOG_THREAD_LOCAL.set(log);
    }

    public void printLogInfo(String message, Throwable throwable) {
        Log logger = LOG_THREAD_LOCAL.get();
        if (logger == null) {
            return;
        }
        log.info(logger.getMessage(), throwable);
    }
}
