package com.wsw.patrickstar.algorithm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @Author WangSongWen
 * @Date: Created in 16:28 2021/8/24
 * @Description: 自定义库分片策略
 */
public class TaskDataBaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String timeValue = preciseShardingValue.getValue();
        String time = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        // 按年路由
        for (String each : collection) {
            String value = StringUtils.substring(timeValue, 0, 4);
            if (each.endsWith(value)) {
                return each;
            }
        }
        return null;
    }

}
