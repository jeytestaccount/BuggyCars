package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ConfigReader;


/**
 * This page object class is used to perform actions on Home Page
 *
 * author Jey
 */
public class HomePage extends BasePage {

    private WebDriver driver;
    private ConfigReader reader = new ConfigReader();
    private Logger logger = LoggerFactory.getLogger(HomePage.class);


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void getHomePage() {
        visit(reader.getUrl());
    }

    public void login(String username, String password) {
        type(username, By.name("login"));
        type(password, By.name("password"));
        click(By.xpath("//*[text()='Login']"));
    }

    public void selectListOfAllModels() {
        click(By.xpath("//img[@src='/img/overall.jpg']"));
    }

    public Boolean isLoginSuccess() {
        return isElementExist(By.xpath("//*[text()='Logout']"));
    }

    public Boolean isInvalidErrorExist() {
        return isElementExist(By.xpath("//*[text()='Invalid username/password']"));
    }

    public void selectPopularModel() {
        click(By.xpath("/html/body/my-app/div/main/my-home/div/div[2]/div/a/img"));
    }
}
