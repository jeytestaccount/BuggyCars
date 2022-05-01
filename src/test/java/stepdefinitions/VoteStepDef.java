package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import pageobject.ModelDetailsPage;
import pageobject.HomePage;
import state.ScenarioState;
import utilities.JsonReader;
import utilities.TestDataManager;


/**
 * This class has step definition for Vote and rate functionality
 *
 * author Jey
 */
public class VoteStepDef {

    private HomePage homePage;
    private ScenarioState state;
    private ModelDetailsPage modelDetailsPage;
    private TestDataManager testDataManager = new TestDataManager();
    private int countBeforeVote;
    private int countAfterVote;
    private int expectedCount;
    private JsonReader jsonReader = new JsonReader();
    private JSONObject validData = jsonReader.jsonReader(testDataManager.getLoginData(), "login valid");
    private Logger logger = LoggerFactory.getLogger(VoteStepDef.class);


    public VoteStepDef(ScenarioState state) {
        this.state = state;
        homePage = new HomePage(state.getDriver());
        modelDetailsPage = new ModelDetailsPage(state.getDriver());
    }

    @When("I select a popular model in Buggy car homepage")
    public void iSelectAPopularModelInBuggyCarHomepage() {
        homePage.selectPopularModel();
    }

    @Then("I should be able to view the details of the model")
    public void iShouldBeAbleToViewTheDetailsOfTheModel() {
        Assert.assertTrue("Model Details Page is not loaded",modelDetailsPage.isDetailsPage());
    }

    @And("I should be able to vote successfully")
    public void iShouldBeAbleToVoteSuccessfully() {
        countBeforeVote = modelDetailsPage.getVoteCounts();
        modelDetailsPage.vote();
        Assert.assertTrue("Voting is not successful", modelDetailsPage.isCarVoted());
        logger.info("Voting is done successfully for the popular model");
    }

    @And("vote count should be updated correctly")
    public void voteCountShouldBeUpdatedCorrectly() throws InterruptedException {
        Assert.assertTrue(modelDetailsPage.isCarVoted());
        countAfterVote = modelDetailsPage.getVoteCounts();
        expectedCount = countBeforeVote + 1;
        Assert.assertEquals("Count is not updated correctly", expectedCount, countAfterVote);
    }

    @And("I should not be able to vote as i did not login")
    public void iShouldNotBeAbleToVoteAsIDidNotLogin() {
        Assert.assertFalse(modelDetailsPage.isVoteButtonExist());
        Assert.assertTrue(modelDetailsPage.isLoginMessageExist());
    }
}
