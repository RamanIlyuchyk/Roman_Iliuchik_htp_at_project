package tests.cucumber.booking.create_new_user;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import settings.Config;
import steps.trashmail_yandex.MailSteps;
import steps.trashmail_yandex.TrashMailNewUser;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingNewUser {
    Properties properties;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingNewUser.class);

    @Before
    public void precondition() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
    }

    @Given("I go to trashmail.com")
    public void iGoToTrashmailCom() {
    }

    @Then("I get new trash mail")
    public void iGetNewTrashMail() throws IOException, InterruptedException {
        TrashMailNewUser.trashMailGetNewMail();
    }

    @Then("I go to booking.com")
    public void iGoToBookingCom() {
        Driver.openUrl("https://www.booking.com/");
    }

    @Then("I create new user")
    public void iCreateNewUser() throws IOException, InterruptedException {
        MainPage.bookingRegistration(properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I go to yandex.ru")
    public void iGoToYandexRu() throws IOException, InterruptedException {
        MailSteps.confirmLinkOnYandexMail("booking.com");
    }

    @Then("I confirm email")
    public void iConfirmEmail() throws InterruptedException {
        String currentHandle = Driver.getWebDriver().getWindowHandle();
        Driver.findElementClick("//*[contains(text(),'Подтверждаю')]");
        Set<String> handles = Driver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                Driver.getWebDriver().switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
    }

    @Then("I again go to booking.com")
    public void iAgainGoToBookingCom() throws InterruptedException {
        Driver.openUrl("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I go to user page")
    public void iGoToUserPage() {
        Driver.findElementClick("//*[@id='profile-menu-trigger--content']");
        Driver.findElementClick("//*[contains(@class,'mydashboard')]");
    }

    @Then("I check the lack of a banner")
    public void iCheckTheLackOfABanner() {
        assertEquals(Driver.getWebDriver().findElements(By.xpath("//*[@class='email-confirm-banner']")).size(), 0);
    }

    @After
    public void postcondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}