package tests.cucumber.booking.check_header;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingCheckHeader {
    static Properties properties;
    static List<WebElement> list;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingCheckHeader.class);

    @Before
    public void precondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        list = new ArrayList<>();
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {

    }

    @Then("I log in")
    public void iLogIn() throws InterruptedException {
        MainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I find all header elements")
    public void iFindAllHeaderElements() {
        addToList("//*[@class='header-wrapper']/img");
        addToList("//*[@data-id='currency_selector']");
        addToList("//*[@data-id='language_selector']");
        addToList("//*[@data-id='notifications']");
        addToList("//*[contains(@class,'uc_helpcenter')]");
        addToList("//*[contains(@class,'uc_account logged')]");
        addToList("//*[contains(@id,'current_account')]");
        addToList("//*[contains(@data-ga-track,'accommodation')]");
        addToList("//*[contains(@data-ga-track,'flights')]");
        addToList("//*[contains(@data-ga-track,'cars')]");
        addToList("//*[contains(@data-ga-track,'attractions')]");
        addToList("//*[contains(@data-ga-track,'airport_taxis')]");
    }

    public void addToList(String xPath) {
        list.add(Driver.getWebDriver().findElement(By.xpath(xPath)));
    }

    @Then("I check the number of items found")
    public void iCheckTheNumberOfItemsFound() {
        assertEquals(12, list.size());
    }

    @After
    public void postcondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}