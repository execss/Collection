package com.central.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zlt
 */
@SpringBootApplication
public class BackWebApplication {
	public static void main(String[] args) {

		System.setProperty("apollo.config-service", "http://ip:7000");

		SpringApplication.run(BackWebApplication.class, args);
	}
}
