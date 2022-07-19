package com.wsw.patrickstar.task.algorithm;

import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.text.ParseException;
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
    Map<Integer, List<String>> cacheListMap;

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
                StringBuilder sb = new StringBuilder(logicTable).append("_").append(source);
                int num = cacheMap.get(source);
                if (num == 1) {
                    return sb.toString();
                } else {
                    return sb.append(date.getTime() % num).toString();
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
        if (start == -1 || end == -1) {
            this.addAvailableTargetName(cacheList.get(cacheList.size() - 1), logicTable, tables);
        } else {
            int current;
            for (int i = 0, size = cacheList.size(); i < size; i++) {
                current = cacheList.get(i);
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
            try {
                date = DateUtils.parseDate(obj.toString(), YYYYMMDD);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if (obj instanceof Date) {
            date = (Date) obj;
        }
        if (Objects.nonNull(date)) {
            return Integer.parseInt(DateFormatUtils.format(date, YYYYMMDD));
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
        final List<String> list = cacheListMap.get(current);
        for (String suffix : list) {
            tables.add(logicTable + "_" + suffix);
        }
    }

    private int getDateNum(Date date) {
        Calendar calendar = DateUtils.toCalendar(date);
        return calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void cacheTable(Collection<String> availableTargetNames, String logicTable) {
        cacheMap = new HashMap<>();
        cacheList = new ArrayList<>();
        cacheListMap = new HashMap<>();

        availableTargetNames.forEach(e -> {
            String suffix = e.substring(logicTable.length() + 1);
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
            if (!cacheListMap.containsKey(date)) {
                cacheListMap.put(date, new ArrayList<>(16));
            }
            cacheListMap.get(date).add(suffix);
            cacheMap.put(date, num);
        });
        cacheList.sort(Comparator.comparingInt(a -> a));
    }

}
