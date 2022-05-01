package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import state.ScenarioState;


/**
 * This page object class is used to perform actions on overall list page
 *
 * author Jey
 */
public class OverallPage extends BasePage {

    private ScenarioState state;
    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(HomePage.class);


    public OverallPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * This method search model in overall page in all pages
     * @param value
     */
    public void searchAndClick(String value) {
        By locator = By.xpath("//a[text()='" + value + "']");
        String size = getText(By.xpath("//div[text()[contains(.,'page 1 of')]]")).trim();
        int pageSize = Integer.parseInt(size.substring(14));
        for (int i = 1; i <= pageSize; i++) {
            try {
                if (find(locator).isDisplayed()) {
                    click(locator);
                    break;
                }
            } catch (TimeoutException e) {
                click(By.xpath("//a[text()='»']"));
            }
        }
    }

}