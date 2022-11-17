package com.wsw.patrickstar.task;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableAsync
@EnableOpenApi
@EnableDiscoveryClient
@EnableFeignClients("com.wsw")
@SpringBootApplication(scanBasePackages = {"com.wsw"}, exclude = {SpringBootConfiguration.class, DataSourceAutoConfiguration.class})
public class PatrickStarTaskServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatrickStarTaskServiceApplication.class, args);
    }
}
