package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import pageobject.HomePage;
import pageobject.MakeDetailsPage;
import pageobject.OverallPage;
import state.ScenarioState;
import utilities.JsonReader;
import utilities.TestDataManager;


/**
 * This class has step definition for Cars Make functionality
 *
 * author Jey
 */
public class MakeStepDef {

    private ScenarioState state;
    private HomePage homePage;
    private OverallPage overallPage;
    private MakeDetailsPage makeDetailsPage;
    private TestDataManager testDataManager=new TestDataManager();
    private JsonReader jsonReader = new JsonReader();
    private JSONObject makeTableData = jsonReader.jsonReader(testDataManager.getMakeData(),"make table" );
    private Boolean isAllModelsFound;
    private Logger logger = LoggerFactory.getLogger(VoteStepDef.class);


    public MakeStepDef(ScenarioState state) {
        this.state = state;
        homePage=new HomePage(state.getDriver());
        overallPage = new OverallPage(state.getDriver());
        makeDetailsPage = new MakeDetailsPage(state.getDriver());
    }

    @And("I select a make {string}  in overall model page")
    public void iSelectAMakeInOverallModelPage(String make) {
        overallPage.searchAndClick(make);
    }

    @Then("I should be able to view the list of models by Make {string}")
    public void iShouldBeAbleToViewTheListOfModelsByMake(String make) {
        isAllModelsFound = makeDetailsPage.isAllModelsByMakeFound(make);
        Assert.assertTrue("All models not displayed for make",isAllModelsFound);
        logger.info("All models for the make is found");
    }

    @Then("I should not be able to view a model which is not belongs to the Make {string}")
    public void iShouldNotBeAbleToViewAModelWhichIsNotBelongsToTheMake(String make) {
        isAllModelsFound = makeDetailsPage.isAllModelsByMakeFound(make);
        Assert.assertFalse("Model not related to Make is displayed",isAllModelsFound);
    }

    @And("I should be able to view model table")
    public void iShouldBeAbleToViewModelTable() {
        Assert.assertTrue("Model details table not exists",makeDetailsPage.isModelTableExist());
    }

}
