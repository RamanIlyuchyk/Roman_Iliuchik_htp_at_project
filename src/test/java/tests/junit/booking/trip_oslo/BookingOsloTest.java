package tests.junit.booking.trip_oslo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.net.MalformedURLException;

public class BookingOsloTest {
    BookingPage bookingPage;
    Actions actions;
    int daysAmount = 1;
    int daysShift = 1;
    int adultsNeed = 2;
    int roomsNeed = 1;
    int childrenNeed = 1;
    private static final Logger LOGGER = LogManager.getLogger(BookingOsloTest.class);

    @Before
    public void preCondition() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.clearCookies();
        bookingPage = new BookingPage(Driver.getWebDriver());
        actions = new Actions(Driver.getWebDriver());
    }

    @Test
    public void tripOsloTest() throws InterruptedException {
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        bookingPage.setDataForSearch("Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        bookingPage.sortForOslo();
        bookingPage.actionsForOslo();
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        //Driver.destroy();
    }
}