package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * UserProfile Microservice
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }
}
