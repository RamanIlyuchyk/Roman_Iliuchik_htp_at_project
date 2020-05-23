package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import web_driver.Driver;
import web_pages.booking.HotelsPage;
import web_pages.booking.MainPage;

import java.util.concurrent.TimeUnit;

public class BookingOsloTest {
    int daysShift = 1;
    int daysAmount = 1;
    int adultsNeed = 2;
    int childrenNeed = 1;
    int roomsNeed = 1;
    static String BOOKING_URL = "https://www.booking.com/";
    WebDriver driver;
    WebElement element;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }

    @Test
    public void bookingOsloTest() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        BaseSteps.findElementClick(driver, "//*[@data-id='class-3']");
        BaseSteps.findElementClick(driver, "//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(3);
        element = driver.findElement(By.xpath("//*[@data-hotelid][10]"));
        HotelsPage.scrollIntoViewHighlightSetAttribute(element, driver, new Actions(driver));
        BaseSteps.findElementCheckAttribute(driver, "//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]", "style", "color: red;");
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}