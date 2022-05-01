package state;

import org.openqa.selenium.WebDriver;


/**
 * This class is used for Dependency injection through Pico container
 *
 * author Jey
 */
public class ScenarioState {

    private WebDriver driver;
    private String username;
    private String password;

    public WebDriver getDriver() {
        return driver;
    }

    public ScenarioState setDriver(WebDriver driver) {
        this.driver = driver;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
