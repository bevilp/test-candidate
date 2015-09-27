package com.test.intake.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * IntakeServiceApp
 *
 * @author Ben
 * @since 26/09/2015
 */
@SpringBootApplication
@EnableDiscoveryClient
public class IntakeServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(IntakeServiceApp.class, args);
    }
}
