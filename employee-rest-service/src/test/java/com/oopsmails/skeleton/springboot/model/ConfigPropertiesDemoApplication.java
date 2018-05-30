package com.oopsmails.skeleton.springboot.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses = PropsObj.class)
public class ConfigPropertiesDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigPropertiesDemoApplication.class);
    }
}
