package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.common.CustomException;
import com.yahaha.reggie.entity.Category;
import com.yahaha.reggie.entity.Dish;
import com.yahaha.reggie.entity.Setmeal;
import com.yahaha.reggie.mapper.CategoryMapper;
import com.yahaha.reggie.service.CategoryService;
import com.yahaha.reggie.service.DishService;
import com.yahaha.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-23 09:10:47
 * @description
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 查询当前分类是否关联了菜品，如果已关联，抛出一个业务异常
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0){
            // 已经关联菜品，抛出异常
            throw new CustomException("当前分类项关联了菜品，不能删除");
        }

        // 查询当前分类是否关联了套餐，如果已关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count();
        if (count2 > 0){
            // 已经关联套餐，抛出异常
            throw new CustomException("当前分类项关联了套餐，不能删除");
        }

        // 正常删除
        super.removeById(id);
    }
}
