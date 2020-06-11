package tests.junit.booking.check_header;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import settings.Config;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingCheckHeaderTest {
    static BookingPage bookingPage;
    static Properties properties;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingCheckHeaderTest.class);

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void checkHeaderTest() throws InterruptedException {
        bookingPage.signIn(properties);
        TimeUnit.SECONDS.sleep(3);
        bookingPage.checkHeader();
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}