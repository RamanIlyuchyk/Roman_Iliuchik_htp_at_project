package steps.cucumber.silver_screen.login;

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

public class SilverScreenLogin {
    Properties prop;
    SilverScreenPage silverScreenPage;
    static String SILVER_SCREEN_PATH = "src/test/resources/properties/silverScreen.properties";
    static String SILVER_SCREEN_URL = "https://silverscreen.by";
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenLogin.class);

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

    @When("I login with <login> and <password>")
    public void iLogin() throws InterruptedException {
        silverScreenPage.signIn(prop.getProperty("EMAIL"), prop.getProperty("PASSWORD"));
        LOGGER.info("I signed in with login and password");
    }

    @Then("I can see Red Carpet Club <level> in upper right corner")
    public void iCanSeeRedCarpetClubLevelInUpperRightCorner() throws InterruptedException {
        assertTrue("Red Carpet Club level is not displayed", silverScreenPage.isLevelDisplayed());
        LOGGER.info("I made sure that Red Carpet Club level in upper right corner was shown");
        TimeUnit.SECONDS.sleep(2);
        silverScreenPage.signOut();
    }

    @After
    public void postCondition() {
        Driver.destroy();
    }
}