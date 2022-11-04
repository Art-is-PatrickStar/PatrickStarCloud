package com.wsw.patrickstar.recepienter.mapstruct;

import com.wsw.patrickstar.api.model.dto.TaskRecordDTO;
import com.wsw.patrickstar.recepienter.entity.TaskRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 13:22
 * Copyright © 2022 Hundsun Technologies Inc. All Rights Reserved
 */
@Mapper
public interface IRecepienterConvert {
    IRecepienterConvert INSTANCE = Mappers.getMapper(IRecepienterConvert.class);

    TaskRecordEntity dtoToEntity(TaskRecordDTO dto);

    List<TaskRecordEntity> dtosToEntities(List<TaskRecordDTO> dtos);
}
