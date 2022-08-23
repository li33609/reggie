package com.yahaha.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yahaha.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yahaha
 * @date 2022-08-23 09:41:50
 * @description
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
