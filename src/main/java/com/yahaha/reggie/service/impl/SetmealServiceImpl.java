package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.entity.Setmeal;
import com.yahaha.reggie.mapper.SetmealMapper;
import com.yahaha.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-23 09:45:12
 * @description
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
