package steps.cucumber.booking.check_header;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import settings.Config;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingCheckHeader {
    static BookingPage bookingPage;
    static Properties properties;
    static List<WebElement> list;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingCheckHeader.class);

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        list = new ArrayList<>();
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
    }

    @Then("I sign in")
    public void iSignIn() throws InterruptedException {
        bookingPage.signIn(properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @And("I check all header elements")
    public void iCheckAllHeaderElements() {
        bookingPage.checkHeader();
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}