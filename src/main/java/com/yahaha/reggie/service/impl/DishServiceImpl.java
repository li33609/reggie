package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.dto.DishDto;
import com.yahaha.reggie.entity.Dish;
import com.yahaha.reggie.entity.DishFlavor;
import com.yahaha.reggie.mapper.DishMapper;
import com.yahaha.reggie.service.DishFlavorService;
import com.yahaha.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yahaha
 * @date 2022-08-23 09:44:18
 * @description
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时插入菜品对应的口味数据
     * @param dishDto
     */
    @Transactional // 事务控制
    public void saveWithFlavor(DishDto dishDto) {
        // 保存菜品的基本信息到菜品表dish
        this.save(dishDto);

        Long dishId = dishDto.getId(); // 菜品Id

        List<DishFlavor> flavors =  dishDto.getFlavors(); // 菜品口味
        flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());


        // 保存菜品的口味基本信息到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据ID查询菜品和口味信息
     * @param id
     * @return
     */
    public DishDto getByIdWithFlavor(Long id) {

        // 查询菜品基本信息
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        // 查询菜品口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        // 更新dish表
        this.updateById(dishDto);

        // 清理当前菜品对应的口味数据--delete
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());

        dishFlavorService.remove(queryWrapper);

        // 更新dish_flavor表 -- insert
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }
}
