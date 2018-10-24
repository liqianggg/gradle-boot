package com.liqiang.gradle.gradleboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liqiang.gradle.gradleboot.mapper")
public class GradleBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradleBootApplication.class, args);
    }
}
