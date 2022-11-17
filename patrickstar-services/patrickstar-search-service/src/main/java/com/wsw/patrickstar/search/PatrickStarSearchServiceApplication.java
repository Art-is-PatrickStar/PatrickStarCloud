package com.wsw.patrickstar.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableDiscoveryClient
@EnableFeignClients("com.wsw")
@SpringBootApplication(scanBasePackages = {"com.wsw"})
public class PatrickStarSearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatrickStarSearchServiceApplication.class, args);
    }
}
