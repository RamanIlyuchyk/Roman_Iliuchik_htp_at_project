package tests.junit.booking.add_favorites;

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

public class BookingAddFavoritesTest {
    BookingPage bookingPage;
    Properties properties;
    int daysAmount = 5;
    int daysShift = 30;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingAddFavoritesTest.class);

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        bookingPage.signIn(properties);
        TimeUnit.SECONDS.sleep(3);
        bookingPage.setDataForSearch("Madrid", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(5);
        bookingPage.setFavoritesAndCheckColor();
        bookingPage.compareHotelID(bookingPage.getFirstFavoriteHotel(), bookingPage.getLastFavoriteHotel());
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}