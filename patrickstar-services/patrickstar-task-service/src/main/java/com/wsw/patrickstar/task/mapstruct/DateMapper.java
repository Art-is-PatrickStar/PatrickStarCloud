package com.wsw.patrickstar.task.mapstruct;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: entity与dto日期格式转换
 * @Author: wangsongwen
 * @DateTime: 2022/8/5 10:50
 */
public class DateMapper {
    public String dateToString(Date date) {
        try {
            return date != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date) : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Date stringToDate(String date) {
        try {
            return date != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(date) : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
