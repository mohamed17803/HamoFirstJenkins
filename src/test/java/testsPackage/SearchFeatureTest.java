package testsPackage;


import data.SearchData;
import io.qameta.allure.*;

import org.Pages.SearchFeature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

@Epic("Product Search Functionality")
@Story("Search for products using keywords and validate relevant results")
@Severity(SeverityLevel.BLOCKER)


public class SearchFeatureTest {


    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Validate if the user can search for a product ")
    @Step("Check Using MacBook")
    public void SearchingFeature()  {
        // Setting JSON Path
        String filePath = "src/test/resources/testDatafiles/SearchData.json";

        // Reading  JsonUtils
        SearchData data = JsonUtils.readJsonFile(filePath, SearchData.class);

        if (data != null) {
            // Passing Data to the method
            SearchFeature searchFeature = new SearchFeature(driver);
            searchFeature.UrlNavigate().UserSearch(
                    data.getProductName()
            ).CheckItem();

        }



    }







    @AfterMethod
    @Description("Close the browser after the Test")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
