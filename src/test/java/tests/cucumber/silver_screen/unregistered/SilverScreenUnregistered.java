package tests.cucumber.silver_screen.unregistered;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.silver_screen.SilverScreenPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SilverScreenUnregistered {
    Properties prop;
    SilverScreenPage silverScreenPage;
    static String SILVER_SCREEN_PATH = "src/test/resources/properties/silverScreen.properties";
    static String SILVER_SCREEN_URL = "https://silverscreen.by";
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenUnregistered.class);

    @Before
    public void preCondition() throws IOException {
        prop = Driver.getProperties(SILVER_SCREEN_PATH);
        Driver.initDriver(Config.CHROME);
        silverScreenPage = new SilverScreenPage(Driver.getWebDriver());
    }

    @Given("I open an app")
    public void iOpenAnApp() {
        Driver.followTheLinkSetWindowMode(SILVER_SCREEN_URL, ScreenMode.MAXIMIZE);
        LOGGER.info("I opened an app");
    }

    @When("I login as unregistered user")
    public void iLogin() throws InterruptedException {
        silverScreenPage.signIn(prop.getProperty("FAKE_EMAIL"), prop.getProperty("FAKE_PASSWORD"));
        LOGGER.info("I signed in as unregistered user");
    }

    @Then("I see validation message")
    public void iSeeValidationMessage() throws InterruptedException {
        assertTrue("No warning message", silverScreenPage.isBannerForUnregisteredDisplayed());
        LOGGER.info("I made sure that the user was not found");
        TimeUnit.SECONDS.sleep(2);
    }

    @After
    public void postCondition() {
        Driver.destroy();
    }
}