package net.hypixel.api.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager instance;

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }

        return instance;
    }

    private final Properties config = new Properties();

    private ConfigManager() {
        try {
            String fileName = "config.properties";

            InputStream input = ClassLoader.getSystemResourceAsStream(fileName);
            config.load(input);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getConfig() {
        return config;
    }
}
