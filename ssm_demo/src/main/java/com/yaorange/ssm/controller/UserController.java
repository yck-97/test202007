package com.yaorange.ssm.controller;

import com.yaorange.ssm.pojo.User;
import com.yaorange.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @company: yaorange
 * @author: YCK
 * @version: 1.0
 * @create: 16-07-07 16:12:40
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String show(){
        return "hello";
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        return  userService.findAll();
    }

    @GetMapping("/find")
    public User find(Integer id){
     return userService.findById(id);
    }


}
