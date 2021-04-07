package com.micro.service.service1.controller;

import com.micro.service.service1.entity.User;
import com.micro.service.service1.service.UserService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Logger
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/add")
    public Object addUser(User user){
        return userService.addUser(user);
    }

 //   Redisson redisson;
    @RequestMapping("/login")
    public void login(String name){
        userService.asynLogin(name);
 //       RLock lock= redisson.getLock("name");
    }
}
