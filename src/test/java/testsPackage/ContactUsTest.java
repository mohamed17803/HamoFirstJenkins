    package testsPackage;

    import data.ContactUsData;
    import data.RegisterData;
    import io.qameta.allure.*;
    import org.Pages.ContactUsFeature;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.testng.annotations.AfterMethod;
    import org.testng.annotations.BeforeMethod;
    import org.testng.annotations.Test;
    import utils.JsonUtils;

    @Epic("User Support Communication")
    @Story("Submit inquiry through contact form and validate message delivery confirmation")
    @Severity(SeverityLevel.MINOR)

    public class ContactUsTest {

        private WebDriver driver;
        @BeforeMethod
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        @Test(description = "Check if user can use the contact us feature")
        public void ContactUsCheck() throws InterruptedException {

            String dataPath = "src/test/resources/testDatafiles/ContactUs.json";
            ContactUsData contactUsData = JsonUtils.readJsonFile(dataPath, ContactUsData.class);

            String filePath = "src/test/resources/testDatafiles/registerData.json";
            RegisterData data = JsonUtils.readJsonFile(filePath, RegisterData.class);



            assert data != null;

            ContactUsFeature contactUsFeature = new ContactUsFeature(driver);

            contactUsFeature.UrlNavigate().LoginNavigate()
                    .LoginForm(data.getEmail(), data.getPassword())
                    .ClickLogin();

            assert contactUsData != null;
            contactUsFeature.HomeNavigate()
                    .HomeNavigateToContactUsDirect()
                    .FillingForm(contactUsData.getWrittenMail())
                    .ValidateMessage(contactUsData.getConfirmationMessage());

        }








        @AfterMethod
        @Description("Close the browser after the Test")
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }

        }

    }
