package tests.junit.booking.create_new_user;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import settings.Config;
import steps.trashmail_yandex.MailSteps;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingNewUserTest {
    BookingPage bookingPage;
    Properties properties;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String CURRENT_ACCOUNT = "//*[contains(@id,'current_account')]";
    private static final String MY_DASHBOARD = "//*[contains(@class,'mydashboard')]";
    private static final String SENDER = "booking.com";
    private static final String CONFIRM = "//*[contains(text(),'Подтверждаю')]";
    private static final String CHECKER = "//*[@class='email-confirm-banner']";
    private static final Logger LOGGER = LogManager.getLogger(BookingNewUserTest.class);

    @Before
    public void preCondition() throws IOException, InterruptedException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        MailSteps.trashMailGetNewMail();
        Driver.openUrl(BOOKING_URL);
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        LOGGER.debug("I create new user");
        bookingPage.registration(properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
        LOGGER.debug("I go to yandex.ru");
        MailSteps.confirmLinkOnYandexMail(SENDER);
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
        LOGGER.debug("I again go to booking.com");
        Driver.openUrl(BOOKING_URL);
        TimeUnit.SECONDS.sleep(2);
        LOGGER.debug("I go to my dashboard");
        Driver.findElementClick(CURRENT_ACCOUNT);
        Driver.findElementClick(MY_DASHBOARD);
        LOGGER.debug("I check lack of banner");
        assertEquals(Driver.getWebDriver().findElements(By.xpath(CHECKER)).size(), 0);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}