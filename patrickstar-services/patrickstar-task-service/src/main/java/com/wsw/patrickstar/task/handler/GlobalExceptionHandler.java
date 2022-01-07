package com.wsw.patrickstar.task.handler;

import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.common.exception.CloudServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author WangSongWen
 * @Date: Created in 16:41 2021/2/4
 * @Description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CloudServiceException.class)
    @ResponseBody
    public Result<String> cloudServiceExceptionHandler(CloudServiceException e) {
        e.printStackTrace();
        return Result.createFailResult("失败: " + e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.createFailResult("失败: " + e.getMessage());
    }

}
