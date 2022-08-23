package com.yahaha.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yahaha.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Yahaha
 * @date 2022-08-19 10:16:07
 * @description
 */
@Mapper
public interface EmployeeMapper  extends BaseMapper<Employee> {

}
