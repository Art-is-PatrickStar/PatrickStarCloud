package com.wsw.patrickstar.common.utils;

import io.swagger.annotations.ApiModelProperty;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 11:17
 */
public class CompareUtil {
    /**
     * @description: 比较两个实体属性值，返回字段的差异信息
     * @author: wangsongwen
     * @date: 2022/6/23 14:23
     * @param: [obj1, obj2, ignoreArr]
     * @return: java.lang.String
     */
    public static String compareFields(Object obj1, Object obj2, String[] ignoreArr) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> ignoreList = null;
            if (ignoreArr != null && ignoreArr.length > 0) {
                // array转化为list
                ignoreList = Arrays.asList(ignoreArr);
            }
            if (obj1.getClass() == obj2.getClass()) {// 只有两个对象都是同一类型的才有可比性
                Class<?> clazz = obj1.getClass();
                // 获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {// 这里就是所有的属性了
                    String name = pd.getName();// 属性名
                    if (ignoreList != null && ignoreList.contains(name)) {// 如果当前属性选择忽略比较，跳到下一次循环
                        continue;
                    }
                    Method readMethod = pd.getReadMethod();// get方法
                    // 在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(obj1);
                    // 在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(obj2);
                    if (!o1.equals(o2)) {
                        try {
                            //根据属性名获取swagger注解的注释从而获取属性的中文名
                            ApiModelProperty apiModelProperty = clazz.getDeclaredField(name).getAnnotation(ApiModelProperty.class);
                            if (apiModelProperty != null) {
                                name = apiModelProperty.value();
                            }
                        } catch (Exception e) {
                            //反射 clazz.getDeclaredField(name) 不能获取到基类的属性
                            //如果属性获取不到且未被忽略，去基类里面寻找 clazz.getSuperclass().getDeclaredField(name)
                            ApiModelProperty apiModelProperty = clazz.getSuperclass().getDeclaredField(name).getAnnotation(ApiModelProperty.class);
                            if (apiModelProperty != null) {
                                name = apiModelProperty.value();
                            }
                        }
                        stringBuilder.append("【编辑字段】：").append(name).append("；【原值】：").append(o1).append("；【新值】：").append(o2).append("；");
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new Exception("实体比较异常: " + e);
        }
    }
}
