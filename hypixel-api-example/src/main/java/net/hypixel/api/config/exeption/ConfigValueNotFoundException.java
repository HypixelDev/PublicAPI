package net.hypixel.api.config.exeption;

public class ConfigValueNotFoundException extends RuntimeException {
    public ConfigValueNotFoundException(String key) {
        super(key + " not found in config. Please check your config file");
    }
}
