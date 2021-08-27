package com.wsw.patrickstar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsw.patrickstar.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
