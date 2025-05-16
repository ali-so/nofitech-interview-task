package com.nofitech.service;

import java.io.IOException;

public interface IWritePropertiesFileService {
    void writePropertiesFile(String key, String value) throws IOException;
}
