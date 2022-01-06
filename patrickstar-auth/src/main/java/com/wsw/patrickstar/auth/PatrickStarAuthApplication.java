package com.wsw.patrickstar.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PatrickStarAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatrickStarAuthApplication.class, args);
    }

}
