package com.yahaha.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.reggie.dto.SetmealDto;
import com.yahaha.reggie.entity.Setmeal;

import java.util.List;

/**
 * @author Yahaha
 * @date 2022-08-23 09:43:10
 * @description
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐同时需要保存菜品和套餐的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时删除套餐和菜品的关联信息
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
