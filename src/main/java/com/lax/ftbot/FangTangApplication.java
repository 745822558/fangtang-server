package com.lax.ftbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class FangTangApplication {

    public static void main(String[] args) {
        SpringApplication.run(FangTangApplication.class, args);
    }

}
