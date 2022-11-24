package com.wsw.patrickstar.rabbitmq.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.wsw"})
public class PatrickstarRabbitmqServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatrickstarRabbitmqServiceApplication.class, args);
    }

}
