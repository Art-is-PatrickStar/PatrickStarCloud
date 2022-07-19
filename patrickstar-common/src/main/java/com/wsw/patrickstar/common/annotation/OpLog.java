package com.wsw.patrickstar.common.annotation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsw.patrickstar.common.enums.ModuleTypeEnum;
import com.wsw.patrickstar.common.enums.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 操作日志注解
 * @Author: wangsongwen
 * @DateTime: 2022/6/21 10:35
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {
    /**
     * 操作类型
     */
    OperationType opType() default OperationType.ADD;

    /**
     * 日志类型
     */
    ModuleTypeEnum type() default ModuleTypeEnum.TASK;

    /**
     * 日志类型对应的主键id
     */
    String typeId() default "";

    /**
     * service类
     */
    Class serviceClass() default IService.class;

    /**
     * 忽略记录变化字段
     */
    String[] ignoreFields() default "";
}
