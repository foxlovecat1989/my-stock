package com.ed.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WatchListApplication {
    public static void main(String[] args) {
        SpringApplication.run(WatchListApplication.class, args);
    }
}
