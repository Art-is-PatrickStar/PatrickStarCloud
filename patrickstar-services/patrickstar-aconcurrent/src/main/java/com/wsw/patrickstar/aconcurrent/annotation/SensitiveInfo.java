package com.wsw.patrickstar.aconcurrent.annotation;

import com.wsw.patrickstar.aconcurrent.enums.SensitiveType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author WangSongWen
 * @Date: Created in 14:19 2021/3/15
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveInfo {
    SensitiveType type();
}
