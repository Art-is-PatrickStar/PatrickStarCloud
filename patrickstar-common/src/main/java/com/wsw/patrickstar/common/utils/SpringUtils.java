package com.wsw.patrickstar.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 11:22
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    protected static ApplicationContext CONTEXT;

    public SpringUtils() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static <T> Object getBean(String name) throws BeansException {
        return CONTEXT.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return CONTEXT.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return CONTEXT.getBean(requiredType);
    }

    public static <T> Map<String, T> getBeans(Class<T> requiredType) {
        return CONTEXT.getBeansOfType(requiredType);
    }

    public static boolean containsBean(String name) {
        return CONTEXT.containsBean(name);
    }
}
