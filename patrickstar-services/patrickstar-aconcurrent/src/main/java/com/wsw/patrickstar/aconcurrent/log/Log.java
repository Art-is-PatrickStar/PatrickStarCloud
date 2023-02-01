package com.wsw.patrickstar.aconcurrent.log;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 10:05 2020/12/29
 * @Description:
 */
public class Log implements Serializable {
    private Long id;
    private Long runTime = 0L;
    private String name = "";
    private String message = "";

    public Long getId() {
        return id;
    }

    public Log setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRunTime() {
        return runTime;
    }

    public Log setRunTime(Long runTime) {
        this.runTime = runTime;
        return this;
    }

    public String getName() {
        return name;
    }

    public Log setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Log setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", runTime=" + runTime +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public Log nowRunTime() {
        this.runTime = System.currentTimeMillis();
        return this;
    }
}
