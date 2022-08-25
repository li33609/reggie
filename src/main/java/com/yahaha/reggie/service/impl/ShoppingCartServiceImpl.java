package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.entity.ShoppingCart;
import com.yahaha.reggie.mapper.ShoppingCartMapper;
import com.yahaha.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-25 09:59:58
 * @description
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
