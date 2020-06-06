package tests.cucumber.silver_screen.blank_field;

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

import static org.junit.Assert.assertTrue;

public class SilverScreenBlankField {
    Properties prop;
    SilverScreenPage silverScreenPage;
    static String SILVER_SCREEN_PATH = "src/test/resources/properties/silverScreen.properties";
    static String SILVER_SCREEN_URL = "https://silverscreen.by";
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenBlankField.class);

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

    @When("^I left blank (.*) field$")
    public void iLeftBlankField(String field) throws InterruptedException {
        switch (field) {
            case ("login"):
                silverScreenPage.blankEmail(prop.getProperty("FAKE_PASSWORD"));
                LOGGER.info("I left blank login field");
                break;
            case ("password"):
                silverScreenPage.blankPassword(prop.getProperty("FAKE_EMAIL"));
                LOGGER.info("I left blank login field");
                break;
        }
    }

    @Then("^I see (.*) message$")
    public void iSeeMessage(String message) {
        assertTrue("This message isn't expected", silverScreenPage.isBannerAboutMistakeDisplayed(message));
        LOGGER.info("I made sure that a recommendation message was shown");
    }
}