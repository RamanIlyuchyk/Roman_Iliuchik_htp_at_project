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
    private static final String YANDEX_URL = "https://mail.yandex.ru/";
    private static final String CURRENT_ACCOUNT_XPATH = "//*[contains(@id,'current_account')]";
    private static final String MY_DASHBOARD_XPATH = "//*[contains(@class,'mydashboard')]";
    private static final String CONTAINS_SENDER_XPATH="//*[contains(text(),'%s')]";
    private static final String SENDER = "booking.com";
    private static final String CONFIRM_XPATH = "//*[contains(text(),'Подтверждаю')]";
    private static final String CHECKER_XPATH = "//*[@class='email-confirm-banner']";
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
        TimeUnit.SECONDS.sleep(5);
        LOGGER.debug("I go to mail.yandex.ru");
        Driver.getWebDriver().get(YANDEX_URL);
        TimeUnit.SECONDS.sleep(5);
        String currentHandle = Driver.getWebDriver().getWindowHandle();
        TimeUnit.SECONDS.sleep(2);
        if(!MailSteps.firstTime){
            Driver.findElementClick(String.format(CONTAINS_SENDER_XPATH, SENDER));
        }
        else {
            MailSteps.confirmLinkOnYandexMail(SENDER);
        }
        TimeUnit.SECONDS.sleep(5);
        Driver.findElementClick(CONFIRM_XPATH);
        TimeUnit.SECONDS.sleep(8);
        Set<String> handles = Driver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                Driver.getWebDriver().switchTo().window(currentHandle);
            }
        }
        LOGGER.debug("I again go to booking.com");
        Driver.getWebDriver().get(BOOKING_URL);
        TimeUnit.SECONDS.sleep(3);
        LOGGER.debug("I go to my dashboard");
        Driver.findElementClick(CURRENT_ACCOUNT_XPATH);
        TimeUnit.SECONDS.sleep(3);
        Driver.findElementClick(MY_DASHBOARD_XPATH);
        TimeUnit.SECONDS.sleep(3);
        LOGGER.debug("I check lack of banner");
        assertEquals(Driver.getWebDriver().findElements(By.xpath(CHECKER_XPATH)).size(), 0);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}