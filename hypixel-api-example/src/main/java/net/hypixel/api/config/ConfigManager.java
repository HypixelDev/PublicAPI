package net.hypixel.api.config;

import net.hypixel.api.config.exeption.ConfigValueNotFoundException;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager instance;
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

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }

        return instance;
    }

    public String get(String key) {
        String value = config.getProperty(key);
        if (value == null) {
            throw new ConfigValueNotFoundException(key);
        }

        return value;
    }

}
