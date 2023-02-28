package com.ed.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.ed.app.clients"
)
public class AppUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppUserApplication.class, args);
    }
}
