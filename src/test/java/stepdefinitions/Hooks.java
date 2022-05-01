package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import state.ScenarioState;
import utilities.WebDriverUtils;


/**
 * This class is used to run methods before and after each scenario
 *
 * author Jey
 */
public class Hooks {

    private ScenarioState state;
    private WebDriver driver;
    private WebDriverUtils webDriverUtils;

    public Hooks(ScenarioState state) {
        this.state = state;
    }

    @Before
    public void setUp() {
        webDriverUtils = new WebDriverUtils();
        driver = webDriverUtils.getDriver();
        state.setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
        webDriverUtils.closeDriver(driver);
    }

}
