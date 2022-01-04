package com.wsw.patrickstar.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 15:04 2020/11/11
 * @Description: 统一结果返回对象
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3046478449948008324L;

    private Integer status;
    private String msg;
    private T data;
    private Boolean success;
    private Long startTime = -1L;
    private Long timeConsume;

    public static <W> Result<W> createFailResult() {
        return createFailResult(ResultStatusEnums.FAILED);
    }

    public static <W> Result<W> createFailResult(String msg) {
        return createFailResult(ResultStatusEnums.FAILED, msg);
    }

    public static <W> Result<W> createFailResult(ResultStatusEnums resultStatusEnums) {
        return createFailResult(resultStatusEnums, resultStatusEnums.getMsg());
    }

    public static <W> Result<W> createFailResult(ResultStatusEnums statusEnums, String msg) {
        return createFailResult(statusEnums.getStatus(), msg);
    }

    public static <W> Result<W> createFailResult(Integer status, String msg) {
        Result<W> ret = new Result<W>();
        ret.setSuccess(false);
        ret.setStatus(status);
        ret.setMsg(msg);
        ret.startTime = System.currentTimeMillis();
        ret.timeConsume = System.currentTimeMillis() - ret.startTime;
        return ret;
    }

    public static <W> Result<W> createSuccessResult(String msg) {
        Result<W> ret = new Result<W>();
        ret.setSuccess(true);
        ret.setStatus(ResultStatusEnums.SUCCESS.getStatus());
        ret.setMsg(msg);
        ret.startTime = System.currentTimeMillis();
        ret.timeConsume = System.currentTimeMillis() - ret.startTime;
        return ret;
    }

    public static <T> Result<T> createSuccessResult(T val) {
        Result<T> ret = new Result<T>();
        ret.setSuccess(true);
        ret.setStatus(ResultStatusEnums.SUCCESS.getStatus());
        ret.setMsg(ResultStatusEnums.SUCCESS.getMsg());
        ret.startTime = System.currentTimeMillis();
        ret.timeConsume = System.currentTimeMillis() - ret.startTime;
        ret.value(val);
        return ret;
    }

    public Result<T> value(T val) {
        if (val == null) {
            return this;
        }
        this.success = true;
        this.data = val;
        this.setStatus(ResultStatusEnums.SUCCESS.getStatus());
        this.setMsg(ResultStatusEnums.SUCCESS.getMsg());
        this.timeConsume = System.currentTimeMillis() - this.startTime;
        return this;
    }
}
