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
    int daysShift = 3;
    int daysAmount = 7;
    int adultsNeed = 4;
    int childrenNeed = 0;
    int roomsNeed = 2;
    static String BOOKING_URL = "https://www.booking.com/";
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }

    @Test
    public void bookingParisTest() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'sort_price')]");
        BaseSteps.findElementClick(driver, "//*[@data-id='pri-5']");
        TimeUnit.SECONDS.sleep(3);
        String budgetPerNight = BaseSteps.findElementGetText(driver, "//*[@data-id='pri-5']").replaceAll("\\D+", "");
        String minFromMax = BaseSteps.findElementGetText(driver, "(//*[contains(@class,'bui-price-display')]/div[2]/div)[1]").replaceAll("\\D+", "");
        int hotelPerNight = Integer.parseInt(minFromMax) / daysAmount;
        System.out.println("Budget per night from " + budgetPerNight);
        System.out.println("Minimum price per night from " + hotelPerNight);
        assertTrue("Something wrong", hotelPerNight >= Integer.parseInt(budgetPerNight));
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}