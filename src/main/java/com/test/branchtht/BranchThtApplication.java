package com.test.branchtht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BranchThtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BranchThtApplication.class, args);
    }

}
