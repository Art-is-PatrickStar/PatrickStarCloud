package com.wsw.patrickstar.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsw.patrickstar.log.entity.OperationLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 14:07
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLogEntity> {
}
