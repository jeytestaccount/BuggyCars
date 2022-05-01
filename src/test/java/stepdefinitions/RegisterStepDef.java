package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import pageobject.RegisterPage;
import state.ScenarioState;
import utilities.JsonReader;
import utilities.TestDataManager;
import java.util.ArrayList;


/**
 * This class has step definition for Registration functionality
 *
 * author Jey
 */
public class RegisterStepDef {


    private ScenarioState state;
    private RegisterPage registerPage;
    private JsonReader jsonReader = new JsonReader();
    private TestDataManager testDataManager = new TestDataManager();
    private ArrayList<String> userCredentials = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(RegisterStepDef.class);
    private JSONObject validData = jsonReader.jsonReader(testDataManager.getRegisterData(),"register valid" );
    private JSONObject invalidData = jsonReader.jsonReader(testDataManager.getRegisterData(),"register invalid" );

    public RegisterStepDef(ScenarioState state) {
        this.state = state;
        registerPage=new RegisterPage(state.getDriver());
    }

    @When("I register with valid user details")
    public void iRegisterWithValidUserDetails() {
        registerPage.clickRegisterOption();
        registerPage.registerWithDetails(validData);
        userCredentials = registerPage.getUserCredentials();
    }

    @Then("I should be able to register successfully")
    public void iShouldBeAbleToRegisterSuccessfully() {
        Assert.assertTrue("Registration failed",registerPage.isRegistrationSuccess());
        logger.info("Registration is successful");
        this.state.setUsername(userCredentials.get(0));
        this.state.setPassword(userCredentials.get(1));
    }

    @When("I register with invalid user details")
    public void iRegisterWithInvalidUserDetails() {
        registerPage.clickRegisterOption();
        registerPage.registerWithDetails(invalidData);
    }

    @Then("I should not be able to register successfully")
    public void iShouldNotBeAbleToRegisterSuccessfully() {
        Assert.assertFalse(registerPage.isRegistrationSuccess());

    }

    @And("I should get an invalid parameter error message")
    public void iShouldGetAnInvalidParameterErrorMessage() {
        Assert.assertTrue("Invalid error not displayed",registerPage.isInvalidErrorExist());
    }
}
