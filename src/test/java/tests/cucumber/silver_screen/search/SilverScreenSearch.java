package tests.cucumber.silver_screen.search;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
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

public class SilverScreenSearch {
    Properties prop;
    SilverScreenPage silverScreenPage;
    static String SILVER_SCREEN_PATH = "src/test/resources/properties/silverScreen.properties";
    static String SILVER_SCREEN_URL = "https://silverscreen.by";
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenSearch.class);

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

    @When("I search for <search word> word")
    public void iSearchForWord() {
        silverScreenPage.findMovie(prop.getProperty("SEARCH_WORD"));
        LOGGER.info("I searched for word");
    }

    @Then("I see the list of movie items")
    public void iSeeTheListOfMovieItems() throws InterruptedException {
        silverScreenPage.seeListOfMovies();
        LOGGER.info("I saw the list of movie items");
        TimeUnit.SECONDS.sleep(3);
    }

    @And("each item name or description contains <search word>")
    public void eachItemNameOrDescriptionContainsWord() {
        assertTrue(silverScreenPage.checkSearchWord(prop.getProperty("SEARCH_WORD")));
        LOGGER.info("I made sure that each item name or description contains search word");
    }

    @After
    public static void postCondition() {
        Driver.destroy();
    }
}