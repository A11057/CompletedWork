package com.amica.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class VPN {

    public enum Status {RUNNING, STOPPED, STARTING}

    public static void main(String[] args) {

//        System.setProperty("management.endpoints.web.exposure.include", "*");
//        System.setProperty("management.endpoint.shutdown.enabled", "true");
//        System.setProperty("endpoints.shutdown.enabled", "true");

//        System.setProperty("logging.level.com.amica.network.Monitor", "INFO");

        System.setProperty("env.name", "developerworkstation");

        SpringApplication.run(VPN.class, args);
    }
}
