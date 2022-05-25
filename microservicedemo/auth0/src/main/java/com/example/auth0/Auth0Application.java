package com.example.auth0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Auth0Application {

    public static void main(String[] args) {
        SpringApplication.run(Auth0Application.class, args);
    }

}
