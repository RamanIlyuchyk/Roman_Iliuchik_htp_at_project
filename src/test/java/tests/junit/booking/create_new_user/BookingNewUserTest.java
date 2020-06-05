package tests.junit.booking.create_new_user;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import settings.Config;
import steps.trashmail_yandex.MailSteps;
import steps.trashmail_yandex.TrashMailNewUser;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingNewUserTest {
    Properties properties;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingNewUserTest.class);

    @Before
    public void preCondition() throws IOException, InterruptedException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        TrashMailNewUser.trashMailGetNewMail();
        Driver.openUrl("https://www.booking.com/");
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        MainPage.bookingRegistration(properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
        MailSteps.confirmLinkOnYandexMail("booking.com");
        String currentHandle = Driver.getWebDriver().getWindowHandle();
        Driver.findElementClick("//*[contains(text(),'Подтверждаю')]");
        Set<String> handles = Driver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                Driver.getWebDriver().switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
        Driver.openUrl("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
        Driver.findElementClick("//*[@id='profile-menu-trigger--content']");
        Driver.findElementClick("//*[contains(@class,'mydashboard')]");
        assertEquals(Driver.getWebDriver().findElements(By.xpath("//*[@class='email-confirm-banner']")).size(), 0);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}