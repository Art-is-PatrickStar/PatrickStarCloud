package com.wsw.patrickstar.mapper;

import com.wsw.patrickstar.domain.Recepienter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecepienterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(@Param("taskId") Long taskId, @Param("taskName") String taskName, @Param("name") String name, @Param("remark") String remark);

    int insertSelective(Recepienter record);

    Recepienter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recepienter record);

    int updateByPrimaryKey(Recepienter record);
}