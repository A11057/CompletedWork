package com.amica.network;

import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Log
@Setter
public class Monitor {

    //Spring book page 45 and Billing8
    //https://codingnconcepts.com/spring-boot/spring-value-annotation/  ex #4
    @Value("${Monitor.statusfile}")
    private String statusfile;

    @Scheduled(fixedRate = 5000)
    public void schedule() throws IOException {
        try (Stream<String> statusLines = Files.lines(Paths.get(statusfile));) {
            String statusString = statusLines.collect(Collectors.joining("\n")).trim();
            try {
                VPN.Status status = VPN.Status.valueOf(statusString.toUpperCase());
                log.info("Status is: " + statusString);
            } catch(Exception ex){
                log.log(Level.WARNING, "File contents are not a recognized value ", statusString);
                }
        }
    }
}
