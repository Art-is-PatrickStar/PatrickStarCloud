package com.wsw.patrickstar.algorithm;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * @Author WangSongWen
 * @Date: Created in 16:28 2021/8/24
 * @Description: 自定义表分片策略
 */
public class RangeYearTableShardingAlgorithm extends TableShardingAlgorithm implements RangeShardingAlgorithm<Date> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        Range<Date> range = rangeShardingValue.getValueRange();
        Collection<String> result = new ArrayList<>();
        Date lower = null;
        Date upper = null;
        Object lowerObj = range.lowerEndpoint();
        Object upperObj = range.upperEndpoint();
        try {
            if (lowerObj instanceof Date) {
                lower = (Date) lowerObj;
            } else {
                lower = dateParse(lowerObj.toString());
            }
            if (upperObj instanceof Date) {
                upper = (Date) upperObj;
            } else {
                upper = dateParse(upperObj.toString());
            }
        } catch (Exception e) {
            return collection;
        }
        // 定义日期实例
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lower);
        while (calendar.getTime().before(upper) || calendar.getTime().equals(upper)) {
            String value = dateFormat(calendar.getTime());
            collection.forEach(v -> {
                if (v.endsWith(value)) {
                    result.add(v);
                }
            });
            calendar.add(Calendar.YEAR, 1);
        }
        return result;
    }
}
