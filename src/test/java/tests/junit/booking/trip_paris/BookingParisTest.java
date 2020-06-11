package tests.junit.booking.trip_paris;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingParisTest {
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
    }

    @Test
    public void tripParisTest() throws InterruptedException {
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        MainPage.setCityPersonRoomDates("Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
        Driver.findElementClick("//*[contains(@class,'sort_price')]/a");
        Driver.findElementClick("//*[@id='filter_price']//a[5]");
        TimeUnit.SECONDS.sleep(2);
        String maxPrice = Driver.findElementGetText("//*[@id='filter_price']//a[5]").replaceAll("\\D+", "");
        String firstPrice = Driver.findElementGetText("//*[contains(@class,'bui-price-display')]/div[2]/div").replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
        System.out.println("Price: " + maxPrice + "+");
        System.out.println("Min one night price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        //Driver.destroy();
    }
}