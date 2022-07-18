package com.wsw.patrickstar.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsw.patrickstar.task.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 14:29 2020/11/9
 * @Description:
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    /**
     * 分表查询测试
     * @param startDate
     * @param endDate
     * @return
     */
    List<Task> selectTaskByTime1(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Task> selectTaskByTime2(@Param("date") Date date);

    List<Task> selectTaskByTime3(List<Date> timeList);
}
