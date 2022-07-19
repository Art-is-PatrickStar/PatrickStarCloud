package com.wsw.patrickstar.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsw.patrickstar.api.model.dto.TaskRequestDTO;
import com.wsw.patrickstar.task.entity.TaskEntity;
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
public interface TaskMapper extends BaseMapper<TaskEntity> {
    IPage<TaskEntity> selectTask(Page<?> page, @Param("query") TaskRequestDTO taskRequestDTO);

    /**
     * 分表查询测试
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<TaskEntity> selectTaskByTime1(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<TaskEntity> selectTaskByTime2(@Param("date") Date date);

    List<TaskEntity> selectTaskByTime3(List<Date> timeList);
}
