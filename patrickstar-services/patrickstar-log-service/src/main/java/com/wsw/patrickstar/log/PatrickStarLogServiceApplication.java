package com.wsw.patrickstar.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.wsw"})
public class PatrickStarLogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatrickStarLogServiceApplication.class, args);
    }

}
