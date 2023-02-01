package com.wsw.patrickstar.aconcurrent.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.wsw.patrickstar.aconcurrent.annotation.SensitiveInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.lang.reflect.Field;

/**
 * @Author WangSongWen
 * @Date: Created in 14:33 2021/3/15
 * @Description:
 */
public class SecurityUtil {
    public String toJsonString(Object object) {
        try {
            return JSON.toJSONString(object, getValuefilter());
        } catch (Exception exception) {
            return ToStringBuilder.reflectionToString(object);
        }
    }

    private static ValueFilter getValuefilter() {
        // obj-对象 key-字段名 value-字段值
        return (obj, key, value) -> {
            try {
                // 通过反射获取获取每个类的属性
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!field.getName().equals(key)) {
                        continue;
                    }
                    SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
                    if (annotation != null) {
                        switch (annotation.type()) {
                            case NAME:
                                return value.toString().charAt(0) + "**";
                            case PHONE:
                                return StringUtils.substring(value.toString(), 0, 3) + "****" +
                                        StringUtils.substring(value.toString(), -4, value.toString().length());
                            case ID_CARD:
                                return StringUtils.substring(value.toString(), 0, 4) + "************" +
                                        StringUtils.substring(value.toString(), -2, value.toString().length());
                            case IMG:
                                return "";
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return value;
        };
    }
}
