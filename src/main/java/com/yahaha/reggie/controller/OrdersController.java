package com.yahaha.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yahaha.reggie.common.R;
import com.yahaha.reggie.dto.OrdersDto;
import com.yahaha.reggie.entity.Orders;
import com.yahaha.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yahaha
 * @date 2022-08-25 17:33:05
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据:{}", orders);

        ordersService.submit(orders);

        return R.success("下单成功");
    }

    /**
     * 获取订单详情
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R getOrders(Integer page, Integer pageSize){
        Page<OrdersDto> ordersDtoPage = ordersService.userPage(page,pageSize);
        return R.success(ordersDtoPage);
    }

    /**
     * 管理员查看订单信息
     * @param page
     * @param pageSize
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String number,String beginTime,String endTime){

        Page pageInfo = new Page<>(page,pageSize);
        return ordersService.pageQuery(pageInfo,number,beginTime,endTime);
    }
}
