package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobject.HomePage;
import pageobject.ModelDetailsPage;
import pageobject.OverallPage;
import state.ScenarioState;
import utilities.JsonReader;
import utilities.TestDataManager;


/**
 * This class has step definition for comment functionality
 *
 * author Jey
 */
public class CommentStepDef {


    private HomePage homePage;
    private OverallPage overallPage;
    private ModelDetailsPage modelDetailsPage;
    private ScenarioState state;
    private JsonReader jsonReader = new JsonReader();
    private TestDataManager dataManager = new TestDataManager();
    private String commentAdded="";
    private String model;
    private JSONObject commentData = jsonReader.jsonReader(dataManager.getCommentData(),"comment invalid" );
    private Logger logger = LoggerFactory.getLogger(CommentStepDef.class);


    public CommentStepDef(ScenarioState state) {
        this.state = state;
        homePage=new HomePage(state.getDriver());
        overallPage = new OverallPage(state.getDriver());
        modelDetailsPage = new ModelDetailsPage(state.getDriver());
    }

    @When("I select a list of all registered models")
    public void iSelectAListOfAllRegisteredModels() {
        homePage.selectListOfAllModels();
    }

    @And("I select a car with make {string} and model {string} in overall model")
    public void iSelectACarWithMakeAndModelInOverallModel(String make, String model) {
        this.model = model;
        overallPage.searchAndClick(model);
    }

    @Then("I should be able to add valid comment {string} and vote successfully")
    public void iShouldBeAbleToAddValidCommentAndVoteSuccessfully(String comment) {
        Assert.assertTrue("Model details page is not loaded", modelDetailsPage.isDetailsPage());
        modelDetailsPage.addComments(comment);
        commentAdded = comment;
        modelDetailsPage.vote();
        Assert.assertTrue("Voting is not success for: "+model,modelDetailsPage.isCarVoted());
        logger.info("Comments and vote are added successfully for the model: "+model);
    }

    @And("I should be able to see the correct date, author and comments in list of comments")
    public void iShouldBeAbleToSeeTheCorrectDateAuthorAndCommentsInListOfComments() {
        String comment = modelDetailsPage.getComment(commentAdded);
        Assert.assertEquals("Added comment is not displayed correctly for: "+model, commentAdded,comment);
    }

    @Then("I should not be able to add invalid comments and vote")
    public void iShouldNotBeAbleToAddInvalidCommentsAndVote() {
        Assert.assertTrue("Model details page is not loaded", modelDetailsPage.isDetailsPage());
        modelDetailsPage.addComments(commentData.get("lengthy_comments").toString());
        modelDetailsPage.vote();
        Assert.assertTrue("'Comment is too long' error not displayed for:"+model, modelDetailsPage.isCommentErrorExist());
    }

}
