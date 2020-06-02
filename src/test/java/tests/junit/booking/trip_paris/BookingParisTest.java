package tests.junit.booking.trip_paris;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import steps.UsersApiSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingParisTest {
    int daysAmount = 7;
    int daysShift = 3;
    int adultsNeed = 4;
    int childrenNeed = 0;
    int roomsNeed = 2;
    static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @BeforeClass
    public static void preCondition() {
        LOGGER.info("Start test");
        driver = Driver.getWebDriver(Config.CHROME);
    }

    @Test
    public void tripParisTest() throws InterruptedException {
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
        MainPage.setCityPersonRoomDates(driver, "Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'sort_price')]/a");
        BaseSteps.findElementClick(driver, "//*[@id='filter_price']//a[5]");
        TimeUnit.SECONDS.sleep(2);
        String maxPrice = BaseSteps.findElementGetText(driver, "//*[@id='filter_price']//a[5]").replaceAll("\\D+", "");
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class,'bui-price-display')]/div[2]/div").replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Finish test");
        BaseSteps.destroy(driver);
    }
}