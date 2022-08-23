package com.yahaha.reggie.controller;

import com.yahaha.reggie.service.SetmealDishService;
import com.yahaha.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yahaha
 * @date 2022-08-23 20:40:19
 * @description 套餐管理
 */
@RestController
@RequestMapping("/Setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;


}
