package com.wsw.patrickstar.task.mapstruct;

import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.task.entity.TaskOperationLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 11:03
 */
@Mapper
public interface ITaskOperationLogConvert {
    ITaskOperationLogConvert INSTANCE = Mappers.getMapper(ITaskOperationLogConvert.class);

    TaskOperationLog dtoToEntity(OpLogDTO dto);

    List<TaskOperationLog> dtosToEntities(List<OpLogDTO> dtos);
}
