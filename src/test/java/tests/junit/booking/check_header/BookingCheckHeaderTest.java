package tests.junit.booking.check_header;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.Config;
import steps.BaseSteps;
import steps.UsersApiSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingCheckHeaderTest {
    static WebDriver driver;
    static Properties properties;
    static String BOOKING_PATH = "src/test/resources/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @BeforeClass
    public static void preCondition() throws IOException {
        LOGGER.info("Start test");
        driver = Driver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(BOOKING_PATH);
    }

    @Test
    public void checkHeadTest() throws InterruptedException {
        MainPage.bookingLogIn(driver, properties);
        TimeUnit.SECONDS.sleep(3);
        assertTrue(BaseSteps.isDisplayed(driver, "//*[@class='header-wrapper']/img"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[@data-id='currency_selector']"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[@data-id='language_selector']"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[@data-id='notifications']"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@class,'uc_helpcenter')]"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@class,'uc_account logged')]"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@id,'current_account')]"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@data-ga-track,'accommodation')]"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@data-ga-track,'flights')]"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@data-ga-track,'cars')]"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@data-ga-track,'attractions')]"));
        assertTrue(BaseSteps.isDisplayed(driver, "//*[contains(@data-ga-track,'airport_taxis')]"));
    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Finish test");
        BaseSteps.destroy(driver);
    }
}