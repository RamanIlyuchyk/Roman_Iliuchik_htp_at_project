package tests.junit.booking.trip_paris;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.net.MalformedURLException;

public class BookingParisTest {
    BookingPage bookingPage;
    int daysAmount = 7;
    int daysShift = 3;
    int adultsNeed = 4;
    int childrenNeed = 0;
    int roomsNeed = 2;
    private static final Logger LOGGER = LogManager.getLogger(BookingParisTest.class);

    @Before
    public void preCondition() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.clearCookies();
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void tripParisTest() throws InterruptedException {
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        bookingPage.setDataForSearch("Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        bookingPage.sortForParis();
        bookingPage.assertForParis(daysAmount);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        //Driver.destroy();
    }
}