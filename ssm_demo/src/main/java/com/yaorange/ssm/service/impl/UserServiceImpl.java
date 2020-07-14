package com.yaorange.ssm.service.impl;

import com.yaorange.ssm.mapper.UserMapper;
import com.yaorange.ssm.pojo.User;
import com.yaorange.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @description:
 * @company: yaorange
 * @author: YCK
 * @version: 1.0
 * @create: 16-07-07 16:20:40
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findById(Integer id) {
//        return userMapper.select(id);
        return null;
    }
}
