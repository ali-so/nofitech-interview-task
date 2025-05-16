package com.nofitech.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class WritePropertiesFileService implements IWritePropertiesFileService {
    private static final String PROPERTIES_FILE = "D:/1.properties";
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void writePropertiesFile(String key, String value) throws IOException {
        lock.lock();
        try {
            File file = new File(PROPERTIES_FILE);

            // Create file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Load existing properties
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream(file)) {
                properties.load(fis);
            }

            // Set the new property
            properties.setProperty(key, value);

            // Save the properties back to the file
            try (FileOutputStream fos = new FileOutputStream(file)) {
                properties.store(fos, "Updated by API");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            lock.unlock();
        }
    }
}
