package steps.cucumber.booking.create_new_user;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import settings.Config;
import steps.trashmail_yandex.MailSteps;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingNewUser {
    BookingPage bookingPage;
    Properties properties;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String SENDER = "booking.com";
    private static final String CONFIRM = "//*[contains(text(),'Подтверждаю')]";
    private static final String CURRENT_ACCOUNT = "//*[contains(@id,'current_account')]";
    private static final String MY_DASHBOARD = "//*[contains(@class,'mydashboard')]";
    private static final String CHECKER = "//*[@class='email-confirm-banner']";
    private static final Logger LOGGER = LogManager.getLogger(BookingNewUser.class);

    @Before
    public void preCondition() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Given("I go to trashmail.com")
    public void iGoToTrashmailCom() {
    }

    @Then("I get new trash mail")
    public void iGetNewTrashMail() throws IOException, InterruptedException {
        MailSteps.trashMailGetNewMail();
    }

    @And("I go to booking.com")
    public void iGoToBookingCom() {
        Driver.openUrl(BOOKING_URL);
    }

    @Then("I create new user")
    public void iCreateNewUser() throws IOException, InterruptedException {
        LOGGER.debug("I create new user");
        bookingPage.registration(properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
    }

    @And("I go to yandex.ru")
    public void iGoToYandexRu() throws IOException, InterruptedException {
        LOGGER.debug("I go to yandex.ru");
        MailSteps.confirmLinkOnYandexMail(SENDER);
    }

    @Then("I confirm email")
    public void iConfirmEmail() throws InterruptedException {
        LOGGER.debug("I confirm email");
        String currentHandle = Driver.getWebDriver().getWindowHandle();
        Driver.findElementClick(CONFIRM);
        Set<String> handles = Driver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                Driver.getWebDriver().switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
    }

    @And("I again go to booking.com")
    public void iAgainGoToBookingCom() throws InterruptedException {
        LOGGER.debug("I again go to booking.com");
        Driver.openUrl(BOOKING_URL);
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I go to my dashboard")
    public void iGoToMyDashBoard() {
        LOGGER.debug("I go to my dashboard");
        Driver.findElementClick(CURRENT_ACCOUNT);
        Driver.findElementClick(MY_DASHBOARD);
    }

    @And("I check lack of banner")
    public void iCheckTheLackOfABanner() {
        LOGGER.debug("I check lack of banner");
        assertEquals(Driver.getWebDriver().findElements(By.xpath(CHECKER)).size(), 0);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}