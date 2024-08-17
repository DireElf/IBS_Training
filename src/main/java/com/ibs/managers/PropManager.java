package com.ibs.managers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class PropManager {
    private final Properties properties = new Properties();
    private static PropManager propManager = null;

    // Private constructor to initialize properties and load them from files
    private PropManager() {
        loadApplicationProperties();
        loadCustomProperties();
    }

    /**
     * Returns the singleton instance of PropManager. If it doesn't exist, a new instance is created.
     * @return the singleton instance of PropManager
     */
    public static PropManager getPropManager() {
        if (propManager == null) {
            propManager = new PropManager();
        }
        return propManager;
    }

    /**
     * Loads the application properties from a file specified by the system property "propFile",
     * or "application.properties" by default.
     */
    private void loadApplicationProperties() {
        try {
            properties.load(Files.newInputStream(new File("src/main/resources/"
                    + System.getProperty("propFile", "application") + ".properties").toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads custom properties, allowing system properties to override values from the properties file.
     */
    private void loadCustomProperties() {
        properties.forEach((key, value) ->
                System.getProperties().forEach((customUserKey, customUserValue) -> {
                    if (key.toString().equals(customUserKey.toString()) &&
                            !value.toString().equals(customUserValue.toString())) {
                        properties.setProperty(key.toString(), customUserValue.toString());
                    }
                }));
    }

    /**
     * Retrieves the value of a property by its key, returning a default value if the property is not found.
     * @param key the key of the property
     * @param defaultValue the default value to return if the property is not found
     * @return the value of the property, or the default value if not found
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Retrieves the value of a property by its key.
     * @param key the key of the property
     * @return the value of the property, or null if not found
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
