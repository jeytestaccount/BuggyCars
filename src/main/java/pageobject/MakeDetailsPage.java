package pageobject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.JsonReader;
import utilities.TestDataManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * This page object class is used to perform actions on Make summary Page
 * which list all the models belongs to the make
 *
 * author Jey
 */
public class MakeDetailsPage extends BasePage {

    private WebDriver driver;
    private String modelNameFound;
    private List<WebElement> tableRows;
    private JsonReader jsonReader = new JsonReader();
    private TestDataManager testDataManager = new TestDataManager();
    private int pageSize;
    private Logger logger = LoggerFactory.getLogger(HomePage.class);
    private final By nextlocator = By.xpath("//a[text()='»']");
    private JSONObject makeTable = jsonReader.jsonReader(testDataManager.getMakeData(), "make table");


    public MakeDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * This method is used to search all models for the make provided and return status
     *
     * @param make
     * @return
     */
    public Boolean isAllModelsByMakeFound(String make) {
        JSONObject modelList = jsonReader.jsonReader(testDataManager.getMakeData(),make);
        tableRows = getTableRowsByName(By.xpath("//table//tr"));
        pageSize = getPageSize(By.xpath("//div[text()[contains(.,'page 1 of')]]"));
        try {
            for (Object key : modelList.keySet()) {
                if (!tableRows.isEmpty()) {
                    loadFirstPage(pageSize);
                    if (!findAllModelsByMake(modelList, key)) {
                        return false;
                    } else { /*do nothing*/ }
                } else {
                    logger.info("The model list Table is not found");
                    return false;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    /**
     * This method search model in all pages of make
     * @param modelList
     * @param key
     * @return
     */
    private Boolean findAllModelsByMake(JSONObject modelList, Object key) {

        for (int i = 1; i <= pageSize; i++) {
            try {
                modelNameFound = find(By.xpath("//a[text()='" + modelList.get(key) + "']")).getText();
                if (modelList.get(key).equals(modelNameFound)) {
                    logger.info("The model: " + modelNameFound + " found");
                    return true;
                }
            } catch (NoSuchElementException | TimeoutException e) {
                if (!isElementExist(nextlocator) || isLastPage()) {
                    return false;
                } else {
                    click(By.xpath("//a[text()='»']"));
                    tableRows = getTableRowsByName(By.xpath("//table//tr"));
                }
            }
        }
        return true;
    }

    public void loadFirstPage(int pageSize) {
        if (pageSize > 1) {
            By pageTextLocated = By.xpath("//input[@type='text']");
            find(pageTextLocated).clear();
            type("1", pageTextLocated);
            type(Keys.ENTER, pageTextLocated);
            find(By.xpath("//div[text()[contains(.,'page 1 of')]]"));
        }

    }

    public int getPageSize(By pageLocator) {
        int pageSize = 0;
        try {
            String size = getText(pageLocator).trim();
            pageSize = Integer.parseInt(size.substring(14));
        } catch (TimeoutException e) {
            pageSize = 1;
        }
        logger.info("The total page size is: " + pageSize);
        return pageSize;
    }

    public Boolean isModelTableExist() {
        List<WebElement> tableHeaders = findAllElements(By.xpath("//table//th"));
        if (!tableHeaders.isEmpty()) {
            Assert.assertEquals(makeTable.get("column 1"), tableHeaders.get(1).getText());
            Assert.assertEquals(makeTable.get("column 2"), tableHeaders.get(2).getText());
            Assert.assertEquals(makeTable.get("column 3"), tableHeaders.get(3).getText());
            Assert.assertEquals(makeTable.get("column 4"), tableHeaders.get(4).getText());
            return true;
        }
        return false;
    }

    public Boolean isLastPage() {
        try {
            if (find(By.xpath("//a[@class='btn disabled' and text()='»']")).isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (TimeoutException e) {
            return false;
        }
    }
}
