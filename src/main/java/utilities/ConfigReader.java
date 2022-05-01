package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;


/**
 * This class is used to read the data from config.properties
 *
 * author Jey
 */
public class ConfigReader {

    private static Properties properties = new Properties();
    ;
    private InputStream inputStream = null;
    private final String propertyFilePath = "src/test/resources/config/config.properties";
    private String browserType;
    private Logger logger = LoggerFactory.getLogger(ConfigReader.class);


    public ConfigReader() {
        try {
            this.inputStream = new FileInputStream(propertyFilePath);
            this.properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Locating Property File" + e);
        } catch (IOException e) {
            throw new RuntimeException("IO Exception occurred" + e);
        }
    }

    public String getUrl() {
        String url = properties.getProperty("URL");
        if (url != null) return new String(url);
        else throw new RuntimeException("URL not specified in config file.");
    }

    public String getKey() {
        String key = properties.getProperty("key");
        if (key != null) return new String(key);
        else throw new RuntimeException("URL not specified in config file.");
    }

    public String getChromeDriverPath() {
        String chromeDriverPath = properties.getProperty("chromeDriverPath");
        if (chromeDriverPath != null) return chromeDriverPath;
        else throw new RuntimeException("Chrome driver path not specified in config file");
    }

    public String getFFDriverPath() {
        String ffDriverPath = properties.getProperty("ffDriverPath");
        if (ffDriverPath != null) return ffDriverPath;
        else throw new RuntimeException("Firefox driver path not specified in config file");
    }

    public String getEdgeDriverPath() {
        String edgeDriverPath = properties.getProperty("edgeDriverPath");
        if (edgeDriverPath != null) return edgeDriverPath;
        else throw new RuntimeException("Edge driver path not specified in config file");
    }
}