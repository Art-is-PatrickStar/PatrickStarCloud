package com.wsw.patrickstar.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EnableDiscoveryClient
public class PatrickStarAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatrickStarAuthServiceApplication.class, args);
    }
}
