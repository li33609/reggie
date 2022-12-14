package com.yahaha.reggie.dto;

import com.yahaha.reggie.entity.OrderDetail;
import com.yahaha.reggie.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author Yahaha
 * @date 2022-08-25 18:12:43
 * @description
 */
@Data
public class OrdersDto extends Orders {

    private List<OrderDetail> orderDetails;
}
