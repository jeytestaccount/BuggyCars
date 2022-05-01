package pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import state.ScenarioState;

import java.util.List;


/**
 * This page object class is used to perform actions on Model summary page
 *
 * author Jey
 */
public class ModelDetailsPage extends BasePage {

    private ScenarioState state;
    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(HomePage.class);


    public ModelDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void vote() {
        click(By.xpath("//button[text()='Vote!']"));
    }

    public void addComments(String comments) {
        type(comments, By.id("comment"));
    }

    public int getVoteCounts() {
        String voteCount = getText(By.xpath("//h4[text()='Votes: ']")).substring(7);
        return Integer.parseInt(voteCount);
    }

    public Boolean isDetailsPage() {
        return isElementExist(By.xpath("//h4[text()='Specification']"));
    }

    public Boolean isLoginMessageExist() {
        return isElementExist(By.xpath("//p[text()='You need to be logged in to vote.']"));
    }

    public Boolean isVoteButtonExist() {
        return isElementExist(By.xpath("//button[text()='Vote!']"));
    }

    public Boolean isCarVoted() {
        return isElementExist(By.xpath("//p[text()='Thank you for your vote!']"));
    }

    public String getComment(String addedComment) {
        WebElement baseTable = find(By.className("table"));
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        String comment = tableRows.get(0).findElement(By.xpath("//td[3]")).getText();
        return comment;
    }

    public Boolean isCommentErrorExist() {
        return isElementExist(By.xpath("//div[text()[contains(.,'comment is too long')]]"));
    }
}
