package com.wsw.patrickstar.common.exception;

import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.response.ResultStatusEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/8/8 17:07
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandler(BusinessException e) {
        log.error("全局异常 - businessExceptionHandler: []", e);
        return Result.createFailResult(e.getResultStatusEnums());
    }

    /**
     * SQL语法异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Result badSqlGrammarHandler(BadSqlGrammarException e) {
        log.error("全局异常 - badSqlGrammarHandler: []", e);
        return Result.createFailResult(ResultStatusEnums.SQL_ERROR_EXCEPTION);
    }

    /**
     * 默认异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("全局异常 - exceptionHandler: []", e);
        if (Objects.nonNull(e.getMessage())) {
            return Result.createFailResult(e.getCause().getCause() + "; " + e.getMessage());
        }
        return Result.createFailResult(ResultStatusEnums.SYSTEM_EXCEPTION);
    }
}
