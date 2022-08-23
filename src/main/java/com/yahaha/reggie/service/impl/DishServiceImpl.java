package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.entity.Dish;
import com.yahaha.reggie.mapper.DishMapper;
import com.yahaha.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-23 09:44:18
 * @description
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
