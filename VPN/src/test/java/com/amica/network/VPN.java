package com.amica.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class VPN {

    public static void main(String[] args) {

        System.setProperty("management.endpoints.web.exposure.include", "*");
        System.setProperty("management.endpoint.shutdown.enabled", "true");
        System.setProperty("endpoints.shutdown.enabled", "true");

        SpringApplication.run(VPN.class, args);
    }
}
