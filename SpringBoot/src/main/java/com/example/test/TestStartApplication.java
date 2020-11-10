package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;

//配置通用的mapper,必须用这个注解
@ComponentScan(basePackages = "com.example.test.**")
@SpringBootApplication
@ConditionalOnWebApplication
//@MapperScan("com.example.test.mapper")

public class TestStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestStartApplication.class, args);
    }

}
