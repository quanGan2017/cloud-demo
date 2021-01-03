package com.example.cloudclient1.controller;


import com.example.cloudclient1.pojo.User;
import com.example.cloudclient1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUserList")
    public List queryUserList(){
        User user = new User();
        List userList =  userService.getUserList();
        System.out.println("我是服务器1");
        return userList;
    }
}
