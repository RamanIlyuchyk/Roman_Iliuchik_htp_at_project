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

import static org.junit.Assert.assertEquals;

public class BookingOsloTest {
    int daysAmount = 1;
    int daysShift = 1;
    int adultsNeed = 2;
    int roomsNeed = 1;
    int childrenNeed = 1;
    WebElement element;
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Test
    public void tripOsloTest() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
        BaseSteps.findElementClick(driver, "//*[@data-id='class-3']");
        BaseSteps.findElementClick(driver, "//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(4);
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]"));
        TimeUnit.SECONDS.sleep(2);
        Actions actions = new Actions(driver);
        element = HotelsPage.executorSetBackgroundTitleColor(element, driver, actions);
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        assertEquals("color: red;", textColor);
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}