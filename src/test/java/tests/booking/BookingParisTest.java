package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import steps.booking.SpecialSteps;
import web_driver.Driver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingParisTest {
    int daysShift = 3;
    int daysAmount = 7;
    int adultsNeed = 4;
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
        BaseSteps.findElementSendKeys(driver, "//*[@id='ss']", "Paris");
        BaseSteps.findElementClick(driver, "//*[@data-mode='checkin']");
        BaseSteps.findElementClick(driver, String.format("//*[@data-date='%s']", SpecialSteps.setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[@data-date='%s']", SpecialSteps.setDays(daysShift + daysAmount)));

        BaseSteps.findElementClick(driver, "//*[@id='xp__guests__toggle']");
        int adultsAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-adult')]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[@aria-describedby='group_adults_desc'][2]", adultsAmount, adultsNeed);
        int roomsAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-rooms')]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[@aria-describedby='no_rooms_desc'][2]", roomsAmount, roomsNeed);
        BaseSteps.findElementClick(driver, "(//*[contains(@type,'submit')])[1]");
        TimeUnit.SECONDS.sleep(3);

        BaseSteps.findElementClick(driver, "//*[contains(@class,'sort_price')]");
        TimeUnit.SECONDS.sleep(3);
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