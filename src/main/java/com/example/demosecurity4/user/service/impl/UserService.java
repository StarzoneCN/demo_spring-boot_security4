package com.example.demosecurity4.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demosecurity4.user.entity.Users;
import com.example.demosecurity4.user.mapper.UsersMapper;
import com.example.demosecurity4.user.service.IUsersService;
import org.springframework.stereotype.Service;

/**
 * @author: LiHongxing
 * @email: lihongxing@bluemoon.com.cn
 * @date: Create in 2018/5/17 10:07
 * @modefied:
 */
@Service
public class UserService extends ServiceImpl<UsersMapper, Users> implements IUsersService {
}
