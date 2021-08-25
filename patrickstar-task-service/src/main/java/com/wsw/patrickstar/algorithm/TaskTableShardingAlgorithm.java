package com.wsw.patrickstar.algorithm;

import com.google.common.collect.Range;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.text.ParseException;
import java.util.*;

/**
 * @Author WangSongWen
 * @Date: Created in 16:28 2021/8/24
 * @Description: 自定义表分片策略
 */
public class TaskTableShardingAlgorithm implements PreciseShardingAlgorithm<Date>, RangeShardingAlgorithm<Date> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        Date date = preciseShardingValue.getValue();
        String columnName = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        String suffixByYearMonth = getSuffixByYearMonth(date);
        for (String tableName : availableTargetNames) {
            if (tableName.endsWith(suffixByYearMonth)) {
                return tableName;
            }
        }
        throw new IllegalArgumentException("未找到匹配的数据表");
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> rangeShardingValue) {
        List<String> list = new ArrayList<>();
        Range<Date> valueRange = rangeShardingValue.getValueRange();
        Date lowerDate = valueRange.lowerEndpoint();
        Date upperDate = valueRange.upperEndpoint();
        String lowerSuffix = getSuffixByYearMonth(lowerDate);
        String upperSuffix = getSuffixByYearMonth(upperDate);
        TreeSet<String> suffixList = getSuffixListForRange(lowerSuffix, upperSuffix);
        for (String tableName : availableTargetNames) {
            if (containTableName(suffixList, tableName)) {
                list.add(tableName);
            }
        }
        return list;
    }

    private boolean containTableName(Set<String> suffixList, String tableName) {
        boolean flag = false;
        for (String s : suffixList) {
            if (tableName.endsWith(s)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public TreeSet<String> getSuffixListForRange(String lowerSuffix, String upperSuffix) {
        TreeSet<String> suffixList = new TreeSet<>();
        if (lowerSuffix.equals(upperSuffix)) { //上下界在同一张表
            suffixList.add(lowerSuffix);
        } else {  //上下界不在同一张表  计算间隔的所有表
            String tempSuffix = lowerSuffix;
            while (!tempSuffix.equals(upperSuffix)) {
                suffixList.add(tempSuffix);
                String[] ym = tempSuffix.split("_");
                Date tempDate = null;
                try {
                    tempDate = DateUtils.parseDate(ym[0] + (ym[1].length() == 1 ? "0" + ym[1] : ym[1]), "yyyyMM");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(tempDate);
                cal.add(Calendar.MONTH, 1);
                tempSuffix = getSuffixByYearMonth(cal.getTime());
            }
            suffixList.add(tempSuffix);
        }
        return suffixList;
    }

    public String getSuffixByYearMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1);
    }

}