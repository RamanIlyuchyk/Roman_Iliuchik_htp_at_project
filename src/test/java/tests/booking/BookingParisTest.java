package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingParisTest {
    int daysAmount = 7;
    int daysShift = 3;
    int adultsNeed = 4;
    int roomsNeed = 2;
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Test
    public void tripParisTest() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Paris", daysAmount, daysShift, adultsNeed, 0, roomsNeed);
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

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}