package com.wsw.patrickstar.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @Author WangSongWen
 * @Date: Created in 16:28 2021/8/24
 * @Description: 自定义表分片策略
 */
public class PreciseYearTableShardingAlgorithm extends TableShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        String value = dateFormat(preciseShardingValue.getValue());
        for (String each : collection) {
            if (each.endsWith(value)) {
                return each;
            }
        }
        throw new UnsupportedOperationException("未找到匹配的数据表");
    }
}
