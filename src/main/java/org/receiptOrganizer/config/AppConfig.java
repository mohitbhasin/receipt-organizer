package org.receiptOrganizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;

@Configuration
public class AppConfig {
    @Autowired
    ResourceLoader resourceLoader;

    String tessdataUri;

    public String getTessdataUri() {
        try {
            tessdataUri = resourceLoader.getResource("classpath:./").getURI().getPath();
        } catch (IOException e) {
            // Cannot access classpath. TESSDATA_PREFIX environment variable will be used.
            tessdataUri = null;
        }
        return tessdataUri;
    }
}
