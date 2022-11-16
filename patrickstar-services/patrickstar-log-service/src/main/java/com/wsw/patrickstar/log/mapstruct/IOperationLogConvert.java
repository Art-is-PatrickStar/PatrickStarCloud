package com.wsw.patrickstar.log.mapstruct;

import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.log.entity.OperationLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 11:03
 */
@Mapper
public interface IOperationLogConvert {
    IOperationLogConvert INSTANCE = Mappers.getMapper(IOperationLogConvert.class);

    OperationLogEntity dtoToEntity(OpLogDTO dto);

    List<OperationLogEntity> dtoToEntity(List<OpLogDTO> dtos);

    OpLogDTO entityToDto(OperationLogEntity entity);

    List<OpLogDTO> entityToDto(List<OperationLogEntity> entities);
}
