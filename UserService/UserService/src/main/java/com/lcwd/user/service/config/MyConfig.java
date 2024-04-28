package com.lcwd.user.service.config;


import com.lcwd.user.service.userService.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(UserServiceImpl.class); // Or you can pass any class name for the logger name
    }

}
