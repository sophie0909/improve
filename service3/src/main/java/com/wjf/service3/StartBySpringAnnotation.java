package com.wjf.service3;

import com.wjf.service3.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartBySpringAnnotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(MyConfig.class);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("======="+beanDefinitionName);
        }
    }
}
