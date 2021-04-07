package com.micro.service.service1.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.micro.service.service1")
@MapperScan(basePackages = {"com.micro.service.service1.mapper"})
public class MyConfig {
}
