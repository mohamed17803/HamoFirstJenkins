package testsPackage;

import data.RegisterData;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonUtils;
@Listeners({AllureTestNg.class})

@Epic("User Authentication")
@Story("Login with valid credentials and validate successful access to user account")
@Severity(SeverityLevel.BLOCKER)

public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test(description = "Validate if the user can sign in using valid data")
    @Step("Validate if the user can sign in")
    public void LoginFeatureTest() throws InterruptedException {
        // Setting JSON Path
        String filePath = "src/test/resources/testDatafiles/registerData.json";

        // Reading  JsonUtils
        RegisterData data = JsonUtils.readJsonFile(filePath, RegisterData.class);

        if (data != null) {
            // Passing Data to the method
            LoginPage loginPage = new LoginPage(driver);
            loginPage.UrlNavigate().LoginNavigate().LoginForm(
                    data.getEmail(), data.getPassword()
            ).ClickLogin().LoginAssert();
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