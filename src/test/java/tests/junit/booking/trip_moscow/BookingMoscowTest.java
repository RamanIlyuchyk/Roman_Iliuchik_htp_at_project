package tests.junit.booking.trip_moscow;

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

public class BookingMoscowTest {
    BookingPage bookingPage;
    Actions actions;
    int daysAmount = 5;
    int daysShift = 10;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    private static final Logger LOGGER = LogManager.getLogger(BookingMoscowTest.class);

    @Before
    public void preCondition() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.clearCookies();
        bookingPage = new BookingPage(Driver.getWebDriver());
        actions = new Actions(Driver.getWebDriver());
    }

    @Test
    public void tripMoscowTest() throws InterruptedException {
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        bookingPage.setDataForSearch("Moscow", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        bookingPage.actionsForMoscow();
        bookingPage.sortForMoscow();
        bookingPage.assertForMoscow(daysAmount);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        //Driver.destroy();
    }
}