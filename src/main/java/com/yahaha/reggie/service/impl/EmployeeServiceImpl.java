package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.entity.Employee;
import com.yahaha.reggie.mapper.EmployeeMapper;
import com.yahaha.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-19 10:19:25
 * @description
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
