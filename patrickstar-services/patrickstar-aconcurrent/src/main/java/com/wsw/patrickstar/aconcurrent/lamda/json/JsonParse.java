package com.wsw.patrickstar.aconcurrent.lamda.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangsongwen
 * @Date: 2021/11/29 16:32
 * @Description:
 */
public class JsonParse {
    public static void main(String[] args) {
        String data = "[{\"ZBSJ\":\"3\"},{\"ZBDM\":\"1010100009\"},{\"JZRQ\":\"2014-02-28\"},{\"XXFBRQ\":\"2014-03-12\"}]";
        Object object = JSON.parse(data);
        if (object instanceof JSONArray) {
            List<Map> antData = JSONArray.parseArray(object.toString(), Map.class);
            System.out.println(antData);
        }
    }
}
