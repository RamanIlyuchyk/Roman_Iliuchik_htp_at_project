package tests.junit.booking.check_header;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import settings.Config;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingCheckHeaderTest {
    static Properties properties;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingCheckHeaderTest.class);

    @BeforeClass
    public static void preCondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
    }

    @Test
    public void checkHeadTest() throws InterruptedException {
        MainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(3);
        assertTrue(Driver.isDisplayed("//*[@class='header-wrapper']/img"));
        assertTrue(Driver.isDisplayed("//*[@data-id='currency_selector']"));
        assertTrue(Driver.isDisplayed("//*[@data-id='language_selector']"));
        assertTrue(Driver.isDisplayed("//*[@data-id='notifications']"));
        assertTrue(Driver.isDisplayed("//*[contains(@class,'uc_helpcenter')]"));
        assertTrue(Driver.isDisplayed("//*[contains(@class,'uc_account logged')]"));
        assertTrue(Driver.isDisplayed("//*[contains(@id,'current_account')]"));
        assertTrue(Driver.isDisplayed("//*[contains(@data-ga-track,'accommodation')]"));
        assertTrue(Driver.isDisplayed("//*[contains(@data-ga-track,'flights')]"));
        assertTrue(Driver.isDisplayed("//*[contains(@data-ga-track,'cars')]"));
        assertTrue(Driver.isDisplayed("//*[contains(@data-ga-track,'attractions')]"));
        assertTrue(Driver.isDisplayed("//*[contains(@data-ga-track,'airport_taxis')]"));
    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}