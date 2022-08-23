package com.yahaha.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.reggie.entity.Category;
import com.yahaha.reggie.entity.Employee;

/**
 * @author Yahaha
 * @date 2022-08-23 09:10:12
 * @description
 */
public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
