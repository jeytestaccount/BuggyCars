package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This class is parent class for all the page object class
 * General methods for all pages can be kept here to achieve re-usability
 *
 * author Jey
 */
public class BasePage {

    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    public void type(Keys key, By locator) {
        find(locator).sendKeys(key);
    }

    public void clear(By locator) {
        find(locator).clear();
    }

    public void click(By locator) {
        find(locator).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void visit(String url) {
        driver.get(url);
    }

    public List<WebElement> findAllElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    public String getText(By locator) {
        return find(locator).getText();
    }

    public Boolean isElementExist(By locator) {
        try {
            if (find(locator).isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        } catch (Exception e) {
            throw e;
        }
        return false;
    }


    public List<WebElement> getTableRowsByName(By tableLocator) {
        List<WebElement> tableRowss = findAllElements(tableLocator);
        return tableRowss;
    }

}