package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import state.ScenarioState;

import java.util.concurrent.TimeUnit;

/**
 * This class is used to perform Web Driver related functions
 *
 * author Jey
 */
public class WebDriverUtils {

    private WebDriver driver;
    private ScenarioState state;
    private Logger logger = LoggerFactory.getLogger(WebDriverUtils.class);
    private ConfigReader reader = new ConfigReader();
    private static final String FIREFOX="firefox";
    private static final String EDGE="edge";
    private static final String CHROME="chrome";

    /**
     * This method is used to create web driver for the browser type provided
     *
     * @return  webDriver
     */
    public WebDriver getDriver() {

        try {
            String browser = System.getProperty("browser");
            if (browser != null && !browser.isEmpty()) {
                switch (browser.toLowerCase()) {

                    case FIREFOX:
                        System.setProperty("webdriver.gecko.driver", reader.getFFDriverPath());
                        driver = new FirefoxDriver();
                        logger.info("Running Test on Firefox Browser");
                        break;
                    case EDGE:
                        System.setProperty("webdriver.edge.driver", reader.getEdgeDriverPath());
                        driver = new EdgeDriver();
                        logger.info("Running Test on Edge Browser");
                        break;
                    case CHROME:
                    default:
                        System.setProperty("webdriver.chrome.driver", reader.getChromeDriverPath());
                        ChromeOptions options = new ChromeOptions();
                        driver = new ChromeDriver(options);
                        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
                        driver.manage().timeouts().setScriptTimeout(6, TimeUnit.SECONDS);
                        driver.manage().deleteAllCookies();
                        driver.manage().window().maximize();
                        logger.info("Running Test on Chrome Browser");
                        break;
                }
            } else {
                System.setProperty("webdriver.chrome.driver", reader.getChromeDriverPath());
                driver = new ChromeDriver();
                logger.info("Running Test on Chrome Browser");
            }
            driver.manage().window().maximize();
            return driver;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This method is used to close the driver
     *
      * @param driver
     */
  public void closeDriver(WebDriver driver) {
        if (driver != null) {
            try {
                driver.close();
            } catch (NoSuchSessionException nssError) { // in case close fails
            } catch (SessionNotCreatedException sncError) {
            } // in case close fails
            driver = null;
        }
    }

}
