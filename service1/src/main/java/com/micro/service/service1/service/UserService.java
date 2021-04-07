package com.micro.service.service1.service;

import com.micro.service.service1.entity.User;
import com.micro.service.service1.mapper.UserMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.misc.Unsafe;

import java.util.HashMap;


@Service
@Log
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int addUser(User user){
        return userMapper.insertSelective(user);
    }

    /**
     * @Async标注的方法，称之为异步方法；这些方法将在执行的时候，
     * 将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作。
     *
     * 相当于程序为执行此方法单独开启了一个线程
     * @param name
     */
    @Async
    public void asynLogin(String name) {
        loginLog(name);
        sendSms(name);
        sendEmail(name);
    }
    private void loginLog(String name) {
        log.info(name+">>>2.异步开始写登陆的日志<<<");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
    }

    private void sendSms(String name) {
        log.info(name+">>>3.异步开始发送短信<<<");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
    }

    private void sendEmail(String name) {
        log.info(name+">>>4.异步开始发送邮件<<<");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
    }
}
