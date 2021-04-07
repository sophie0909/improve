package com.micro.service.service1;

import org.apache.catalina.startup.Tomcat;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.DispatcherServlet;


@MapperScan(basePackages = {"com.micro.service.service1.mapper"})
@SpringBootApplication
@EnableAsync
public class Service1Application {

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

}
