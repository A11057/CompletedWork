package com.amica.network;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Monitor {

    @Scheduled(fixedRate = 5000)
    public void schedule() {


    }
}
