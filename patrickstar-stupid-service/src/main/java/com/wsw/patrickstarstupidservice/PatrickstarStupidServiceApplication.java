package com.wsw.patrickstarstupidservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PatrickstarStupidServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatrickstarStupidServiceApplication.class, args);
    }

}
