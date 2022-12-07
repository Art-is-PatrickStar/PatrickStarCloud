package com.wsw.patrickstar.auth.mapstruct;

import com.wsw.patrickstar.api.model.dto.UserSignUpDTO;
import com.wsw.patrickstar.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/7 16:21
 */
@Mapper
public interface IUserConvert {
    IUserConvert INSTANCE = Mappers.getMapper(IUserConvert.class);

    User DtoToEntity(UserSignUpDTO signUpDTO);
}
