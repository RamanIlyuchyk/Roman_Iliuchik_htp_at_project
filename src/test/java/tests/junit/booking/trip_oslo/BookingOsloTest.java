package tests.junit.booking.trip_oslo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingOsloTest {
    int daysAmount = 1;
    int daysShift = 1;
    int adultsNeed = 2;
    int roomsNeed = 1;
    int childrenNeed = 1;
    WebElement element;
    private static final Logger LOGGER = LogManager.getLogger(BookingOsloTest.class);

    @BeforeClass
    public static void preCondition() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
    }

    @Test
    public void tripOsloTest() throws InterruptedException {
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        MainPage.setCityPersonRoomDates("Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
        Driver.findElementClick("//*[@data-id='class-3']");
        Driver.findElementClick("//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(4);
        element = Driver.findElementReturn("//*[@id='hotellist_inner']/div[11]");
        TimeUnit.SECONDS.sleep(2);
        Actions actions = new Actions(Driver.getWebDriver());
        element = MainPage.executorSetBackgroundTitleColor(element, Driver.getWebDriver(), actions);
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        assertEquals("color: red;", textColor);
    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}