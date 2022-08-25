package com.yahaha.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yahaha.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yahaha
 * @date 2022-08-25 17:28:19
 * @description
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
