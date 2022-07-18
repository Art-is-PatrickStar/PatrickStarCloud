package com.wsw.patrickstar.common.utils;

import com.alibaba.fastjson.JSON;
import com.wsw.patrickstar.common.base.BaseEntity;

import java.util.List;

/**
 * @Author: wangsongwen
 * @Date: 2021/11/17 14:24
 * @Description: TODO
 */
public class SerializeUtils {
    /**
     * 传输对象序列化为json byte
     *
     * @param dto 传输对象-单一对象
     * @return byte数组
     */
    public static <T extends BaseEntity> byte[] serialize2JsonByte(T dto) {
        return JSON.toJSONBytes(dto);
    }

    /**
     * 传输对象序列化json byte
     *
     * @param dtoList 传输对象-列表
     * @return byte数组
     */
    public static <T extends BaseEntity> byte[] serialize2JsonByte(List<T> dtoList) {
        return JSON.toJSONBytes(dtoList);
    }

    /**
     * 传输对象反序列化
     *
     * @param serializedBytes byte数组
     * @param clazz           对象类型
     * @return 传输对象-单一对象
     */
    public static <T extends BaseEntity> T deserialize(byte[] serializedBytes, Class<T> clazz) {
        return JSON.parseObject(serializedBytes, clazz);
    }

    /**
     * 传输对象反序列化
     *
     * @param serializedBytes byte数组
     * @param clazz           对象类型
     * @return 传输对象-列表
     */
    public static <T extends BaseEntity> List<T> deserializeList(byte[] serializedBytes, Class<T> clazz) {
        return JSON.parseArray(new String(serializedBytes), clazz);
    }
}
