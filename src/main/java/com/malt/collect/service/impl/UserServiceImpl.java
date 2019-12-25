package com.malt.collect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.malt.collect.entity.User;
import com.malt.collect.mapper.UserMapper;
import com.malt.collect.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
