package com.wsw.patrickstar.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsw.patrickstar.api.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
