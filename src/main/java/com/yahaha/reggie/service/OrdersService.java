package com.yahaha.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.reggie.dto.OrdersDto;
import com.yahaha.reggie.entity.Orders;

/**
 * @author Yahaha
 * @date 2022-08-25 17:29:54
 * @description
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);


    /**
     * 获取订单详情
     * @param page
     * @param pageSize
     * @return
     */
    Page<OrdersDto> userPage(Integer page, Integer pageSize);
}
