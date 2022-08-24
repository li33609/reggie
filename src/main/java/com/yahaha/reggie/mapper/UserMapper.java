package com.yahaha.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yahaha.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yahaha
 * @date 2022-08-24 15:20:49
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
