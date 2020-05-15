package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import settings.Config;
import steps.BaseSteps;
import steps.MailSteps;
import steps.trashMail.TrashMailNewUser;
import web_driver.Driver;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingNewUserTest {
    Properties properties;
    WebDriver driver;
    protected static String BOOKING_PATH = "src/test/resources/booking.properties";
    static String BOOKING_URL = "https://www.booking.com/";
    static String YANDEX_URL = "https://mail.yandex.ru/";

    @Before
    public void preCondition() throws IOException, InterruptedException {
        driver = Driver.getWebDriver(Config.CHROME);
        TrashMailNewUser.trashMailGetTempMail(driver);
        driver.get(BOOKING_URL);
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        bookingRegistration();
        TimeUnit.SECONDS.sleep(5);
        driver.get(YANDEX_URL);
        TimeUnit.SECONDS.sleep(5);
        String currentHandle = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(2);
        if (!TrashMailNewUser.firstTime) {
            BaseSteps.findElementClick(driver, String.format("//*[contains(text(),'%s')]", "booking.com"));
        } else {
            MailSteps.confirmLinkOnYandexMail(driver, "booking.com");
        }
        TimeUnit.SECONDS.sleep(5);
        BaseSteps.findElementClick(driver, "//*[contains(text(),'Подтверждаю')]");
        TimeUnit.SECONDS.sleep(8);
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
        driver.get(BOOKING_URL);
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementClick(driver, "//*[@id='profile-menu-trigger--content']");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'mydashboard')]");
        TimeUnit.SECONDS.sleep(3);
        assertEquals(0, driver.findElements(By.xpath("//*[@class='email-confirm-banner']")).size());
    }

    public void bookingRegistration() throws IOException, InterruptedException {
        properties = BaseSteps.getProperties(BOOKING_PATH);
        BaseSteps.findElementClick(driver, "//*[@id='current_account_create']");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementSendKeys(driver, "//*[@id='login_name_register']", properties.getProperty("NEW_MAIL"));
        BaseSteps.findElementClick(driver, "//*[contains(@class,'nw-register')]/button");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementSendKeys(driver, "//*[@id='password']", properties.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id='confirmed_password']", properties.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[contains(@type,'submit')]");
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}