package com.wsw.patrickstar.aconcurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class PatrickstarAconcurrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatrickstarAconcurrentApplication.class, args);
    }

}
