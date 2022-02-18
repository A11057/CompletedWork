package com.amica.network;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

@Log
public class MonitorTest {

    private Monitor monitor;
    private String testFilename = "src/test/TestStatus.txt";

    @BeforeEach
    public void setUp() throws IOException {
        monitor = new Monitor();
        monitor.setFilename(testFilename);
    }

    public void setStatus(VPN.Status status) throws IOException {
        try {
            File file = new File(testFilename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(status.toString());
            writer.close();
        } catch(Exception ex){
            log.log(Level.WARNING, "Status is now ", status);
        }
    }

    @Test
    public void testMonitor () throws IOException, InterruptedException {
        monitor.schedule();
        Thread.sleep(8000);
        setStatus(VPN.Status.STOPPED);
        monitor.schedule();
        Thread.sleep(8000);
        setStatus(VPN.Status.STARTING);
        monitor.schedule();
    }
}
