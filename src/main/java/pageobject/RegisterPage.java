package pageobject;


import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.DateGenerator;
import utilities.TextEncryptor;

import java.util.ArrayList;


/**
 * This page object class is used to perform actions on Registration Page
 *
 * author Jey
 */
public class RegisterPage extends BasePage {

    private WebDriver driver;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstname;
    private String lastname;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickRegisterOption() {
        click(By.cssSelector("a[href='/register']"));
    }

    public void registerWithDetails(JSONObject testData) {
        getRegistrationDetails(testData);
        type(username, By.id("username"));
        type(firstname,By.id("firstName"));
        type(lastname,By.id("lastName"));
        type(password, By.id("password"));
        type(confirmPassword, By.id("confirmPassword"));
        clickRegisterButton(By.xpath("/html/body/my-app/div/main/my-register/div/div/form/button"));
    }

    public void clickRegisterButton(By locator){
        click(locator);
    }

    public Boolean isRegistrationSuccess() {
        return isElementExist(By.xpath("//div[text()[contains(.,'Registration is successful')]]"));
    }

    public Boolean isInvalidErrorExist() {
        return isElementExist(By.xpath("//div[text()[contains(.,'InvalidParameter')]]"));
    }

    private void getRegistrationDetails(JSONObject testData) {
        username = testData.get("username").toString() + DateGenerator.getCurrentDataTime();
        password = TextEncryptor.getPassword(testData.get("password").toString());
        confirmPassword = TextEncryptor.getPassword(testData.get("confirm password").toString());
        firstname = testData.get("firstname").toString();
        lastname = testData.get("lastname").toString();
    }

    public ArrayList<String> getUserCredentials() {
        ArrayList<String> userCredentials = new ArrayList<>();
        userCredentials.add(username);
        userCredentials.add(password);
        return userCredentials;
    }

}
