package com.yaorange.ssm.service;

import com.yaorange.ssm.pojo.User;

import java.util.List;

/**
 * @description:
 * @company: yaorange
 * @author: YCK
 * @version: 1.0
 * @create: 16-07-07 16:12:27
 */
public interface UserService {

    List<User> findAll();

    User findById(Integer id);
}
