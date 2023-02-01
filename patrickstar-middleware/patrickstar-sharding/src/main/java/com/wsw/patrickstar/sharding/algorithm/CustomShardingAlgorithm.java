package com.wsw.patrickstar.sharding.algorithm;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 15:09
 */
@Slf4j
@Component
public class CustomShardingAlgorithm implements PreciseShardingAlgorithm<Date>, RangeShardingAlgorithm<Date> {
    private final static String YYYYMMDD = "yyyyMMdd";
    List<Integer> cacheList;
    Map<Integer, Integer> cacheMap;
    //key: 数字类型的日期， value 实际表名
    Map<Integer, List<String>> cachedActualTables;

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        String logicTable = preciseShardingValue.getLogicTableName();
        if (Objects.isNull(cacheList)) {
            synchronized (this) {
                if (Objects.isNull(cacheList)) {
                    cacheTable(availableTargetNames, logicTable);
                }
            }
        }
        Date date = preciseShardingValue.getValue();
        for (int i = cacheList.size() - 1; i >= 0; i--) {
            int source = cacheList.get(i);
            if (getDateNum(date) >= source) {
                List<String> nodes = cachedActualTables.get(source);
                if (nodes != null && nodes.size() >= 0) {
                    return nodes.get(0);
                }
            }
        }
        throw new RuntimeException("根据分表键无法找到对应的表!");
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> rangeShardingValue) {
        List<String> tables = new ArrayList<>(16);
        String logicTable = rangeShardingValue.getLogicTableName();
        if (Objects.isNull(cacheList)) {
            synchronized (this) {
                if (Objects.isNull(cacheList)) {
                    cacheTable(availableTargetNames, logicTable);
                }
            }
        }
        Range<Date> valueRange = rangeShardingValue.getValueRange();
        int start = this.getDate(valueRange.lowerEndpoint());
        int end = this.getDate(valueRange.upperEndpoint());
        int originalStart = cacheList.get(0);
        int originalEnd = cacheList.get(cacheList.size() - 1);
        if (start <= 0) {
            start = originalStart;
        }
        if (start >= originalEnd) {
            start = originalEnd;
        }
        if (end <= 0 || end > originalEnd) {
            end = originalEnd;
        }
        start = this.getStartTable(start);
        end = this.getEndTable(end);
        //这个判断应该是有问题的，会导致开始时间如果小于最早分片中的时间，将返回最大分片。
        if (start == -1 || end == -1) {
            this.addAvailableTargetName(cacheList.get(cacheList.size() - 1), logicTable, tables);
        } else {
            int current;
            for (Integer integer : cacheList) {
                current = integer;
                if (current >= start && current <= end) {
                    this.addAvailableTargetName(current, logicTable, tables);
                }
            }
            if (tables.isEmpty()) {
                this.addAvailableTargetName(cacheList.get(cacheList.size() - 1), logicTable, tables);
            }
        }
        return tables;
    }

    private int getStartTable(int start) {
        int source;
        for (int i = cacheList.size() - 1; i >= 0; i--) {
            source = cacheList.get(i);
            if (start >= source) {
                return source;
            }
        }
        return -1;
    }

    private int getEndTable(int end) {
        int source;
        for (int i = 0, size = cacheList.size(); i < size; i++) {
            source = cacheList.get(i);
            if (source >= end) {
                if (end != source) {
                    if (i == 0) {
                        return -1;
                    } else {
                        return cacheList.get(i - 1);
                    }
                }
                return source;
            }
        }
        return -1;
    }

    private int getDate(Object obj) {
        if (Objects.isNull(obj)) {
            return 0;
        }
        Date date = null;
        if (obj instanceof String) {
            DateTime dateTime = DateUtil.parseDate(obj.toString());
            if (Objects.nonNull(dateTime)) {
                date = dateTime.toJdkDate();
            }
        } else if (obj instanceof Date) {
            date = (Date) obj;
        }
        if (Objects.nonNull(date)) {
            return Integer.parseInt(DateUtil.format(date, YYYYMMDD));
        }
        return 0;
    }

    private boolean isAvailable(int current, int start, int end) {
        boolean flag = false;
        if (current >= start) {
        }
        if (start > 0) {
            if (end <= 0) {
                if (current >= start) {
                    flag = true;
                }
            } else {
                if (current >= start && current <= end) {
                    flag = true;
                }
            }
        } else {
            if (end > 0) {
                if (current <= end) {
                    flag = true;
                }
            } else {
                flag = true;
            }
        }
        return flag;
    }

    private void addAvailableTargetName(int current, String logicTable, List<String> tables) {
        final List<String> list = cachedActualTables.get(current);
        tables.addAll(list);
    }

    private int getDateNum(Date date) {
        Calendar calendar = DateUtil.calendar(date);
        return calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void cacheTable(Collection<String> availableTargetNames, String logicTable) {
        cacheMap = new HashMap<>();
        cacheList = new ArrayList<>();
        cachedActualTables = new HashMap<>();
        availableTargetNames.forEach(e -> {
            String[] split = e.split("_");
            String suffix = split[split.length - 1];
            int date;
            if (suffix.length() > 8) {
                date = Integer.parseInt(suffix.substring(0, 8));
            } else {
                date = Integer.parseInt(suffix);
            }
            int num = 1;
            if (cacheMap.containsKey(date)) {
                num = cacheMap.get(date) + 1;
            }
            if (!cacheList.contains(date)) {
                cacheList.add(date);
            }
            if (!cachedActualTables.containsKey(date)) {
                cachedActualTables.put(date, new ArrayList<>(16));
            }
            cachedActualTables.get(date).add(e);
            cacheMap.put(date, num);
        });
        cacheList.sort(Comparator.comparingInt(a -> a));
    }

}