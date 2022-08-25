package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.entity.OrderDetail;
import com.yahaha.reggie.mapper.OrderDetailMapper;
import com.yahaha.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-25 17:30:41
 * @description
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
