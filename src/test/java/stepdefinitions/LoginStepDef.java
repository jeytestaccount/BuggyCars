package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import pageobject.HomePage;
import state.ScenarioState;
import utilities.JsonReader;
import utilities.TestDataManager;
import utilities.TextEncryptor;


/**
 * This class has step definition for Login functionality
 *
 * author Jey
 */
public class LoginStepDef {

    private HomePage homePage;
    private RegisterStepDef registerStep;
    private ScenarioState state;
    private JsonReader jsonReader = new JsonReader();
    private TestDataManager dataManager=new TestDataManager();
    private String username;
    private String password;
    private Logger logger = LoggerFactory.getLogger(LoginStepDef.class);
    private JSONObject validLoginData = jsonReader.jsonReader(dataManager.getLoginData(), "login valid");
    private JSONObject invalidLoginData = jsonReader.jsonReader(dataManager.getLoginData(), "login invalid");


    public LoginStepDef(ScenarioState state) {
        this.state = state;
        homePage = new HomePage(state.getDriver());
        registerStep = new RegisterStepDef(state);
    }

    @Given("I am at the Buggy car Home Page")
    public void iAmAtTheBuggyCarHomePage() {
        homePage.getHomePage();
    }

    @When("I login with valid User ID and Password")
    public void iLoginWithValidUserIDAndPassword() {
        getLoginCredentials(validLoginData);
        homePage.login(username, password);
    }

    @Then("I should be able to login successfully")
    public void iShouldBeAbleToLoginSuccessfully() {
        Assert.assertTrue("Login is not successful",homePage.isLoginSuccess());
        logger.info("Login is successful");
    }

    @When("I login with invalid User ID and Password")
    public void iLoginWithInvalidUserIDAndPassword() {
        getLoginCredentials(invalidLoginData);
        homePage.login(username, password);
    }

    @Then("I should not be able to login")
    public void iShouldNotBeAbleToLogin() {
        Assert.assertFalse(homePage.isLoginSuccess());
    }

    @And("I should get an error message")
    public void iShouldGetAnErrorMessage() {
        Assert.assertTrue("Login error not exist",homePage.isInvalidErrorExist());
    }

    public void getLoginCredentials(JSONObject loginData) {
        if (state.getUsername() != null && state.getPassword() != null) {
            username = state.getUsername();
            password = state.getPassword();
        } else {
            username = loginData.get("username").toString();
            password = TextEncryptor.getPassword(loginData.get("password").toString());
        }
    }

    @Given("I register with Valid details and login successfully")
    public void iRegisterWithValidDetailsAndLoginSuccessfully() {
        registerStep.iRegisterWithValidUserDetails();
        registerStep.iShouldBeAbleToRegisterSuccessfully();
        iLoginWithValidUserIDAndPassword();
        iShouldBeAbleToLoginSuccessfully();
    }
}
