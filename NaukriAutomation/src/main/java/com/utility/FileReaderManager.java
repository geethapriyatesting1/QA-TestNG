package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileReaderManager {
    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private Properties properties;

    private FileReaderManager() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/TestData.properties")) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load TestData.properties", e);
        }
    }

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ConfigReader getConfigReader() {
        return new ConfigReader(properties);
    }

    public static class ConfigReader {
        private Properties props;
        public ConfigReader(Properties props) { this.props = props; }
        public String getUrl() { return props.getProperty("url"); }
        public String getBrowser() { return props.getProperty("browser"); }
        public int getImplicitWait() { return Integer.parseInt(props.getProperty("implicitWait", "10")); }
        public int getPageLoadTimeout() { return Integer.parseInt(props.getProperty("pageLoadTimeout", "30")); }
        public String getUsername() { return props.getProperty("username"); }
        public String getPassword() { return props.getProperty("password"); }
        public String getResumePath() { return props.getProperty("resumePath"); }
    }
}