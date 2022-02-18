package com.amica.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class VPN {

    public enum Status {RUNNING, STOPPED, STARTING}

    public static void main(String[] args) {

        System.setProperty("env.name", "developerworkstation");

       // System.setProperty("component.name", "MQ");

        SpringApplication.run(VPN.class, args);
    }
}
