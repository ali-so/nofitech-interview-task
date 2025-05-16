package com.nofitech.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class ScheduledReadFileService implements IScheduledReadFileService {
    private final String filePath = "D:/1.properties";
    private Properties latestProperties = new Properties();

    @Override
    public String getProperty(String key) {
        return latestProperties.getProperty(key);
    }

    @Scheduled(fixedRate = 60_000)
    public void reloadProperties() {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fis);
            latestProperties = properties;
            System.out.println("Properties reloaded at " + System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to reload properties file: " + e.getMessage());
        }
    }
}
