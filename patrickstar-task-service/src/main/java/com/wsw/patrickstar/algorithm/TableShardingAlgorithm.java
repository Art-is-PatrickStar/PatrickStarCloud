package com.wsw.patrickstar.algorithm;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * @Author WangSongWen
 * @Date: Created in 10:31 2021/8/26
 * @Description:
 */
public class TableShardingAlgorithm {
    private static final String pattern = "yyyy";

    protected String dateFormat(Date date) {
        return DateFormatUtils.format(date, pattern);
    }

    protected Date dateParse(String str) throws ParseException {
        return FastDateFormat.getInstance(pattern).parse(str);
    }
}
