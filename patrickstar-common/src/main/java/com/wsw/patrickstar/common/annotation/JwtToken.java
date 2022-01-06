package com.wsw.patrickstar.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author WangSongWen
 * @Date: Created in 10:40 2020/11/13
 * @Description: 自定义token验证注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})  // 此注解只能用在方法上
@Retention(RetentionPolicy.RUNTIME)  // 运行时注解生效
public @interface JwtToken {
    // required为true 说明请求必须包含token,不包含报错
    // required为false 说明token不是必须的,不会中断请求的传递,交由业务代码处理
    boolean required() default true;
}
