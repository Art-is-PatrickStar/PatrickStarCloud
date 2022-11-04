package com.wsw.patrickstar.task.mapstruct;

import com.wsw.patrickstar.api.model.dto.TaskDTO;
import com.wsw.patrickstar.task.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 14:37
 */
@Mapper(uses = DateMapper.class)
public interface ITaskConvert {
    ITaskConvert INSTANCE = Mappers.getMapper(ITaskConvert.class);

    TaskDTO entityToDto(TaskEntity taskEntity);

    List<TaskDTO> entitiesToDtos(List<TaskEntity> taskEntities);

    TaskEntity dtoToEntity(TaskDTO taskDTO);

    List<TaskEntity> dtosToEntities(List<TaskDTO> taskDTOS);
}
