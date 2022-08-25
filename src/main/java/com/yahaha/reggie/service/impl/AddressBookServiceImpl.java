package com.yahaha.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.reggie.entity.AddressBook;
import com.yahaha.reggie.mapper.AddressBookMapper;
import com.yahaha.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author Yahaha
 * @date 2022-08-24 16:16:47
 * @description
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
