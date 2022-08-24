package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.entity.User;
import com.yahaha.reggie.mapper.UserMapper;
import com.yahaha.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-24 15:21:42
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
