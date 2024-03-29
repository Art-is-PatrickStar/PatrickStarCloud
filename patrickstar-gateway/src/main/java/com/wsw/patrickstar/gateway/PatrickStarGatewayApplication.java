package com.wsw.patrickstar.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PatrickStarGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatrickStarGatewayApplication.class, args);
    }
}
