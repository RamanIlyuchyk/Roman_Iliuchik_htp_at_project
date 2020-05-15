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

public class BookingOsloTest {
    int daysShift = 1;
    int daysAmount = 1;
    int childrenNeed = 1;
    static String BOOKING_URL="https://www.booking.com/";
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }

    @Test
    public void bookingOsloTest() throws InterruptedException {
        BaseSteps.findElementSendKeys(driver, "//*[@id='ss']", "Oslo");
        BaseSteps.findElementClick(driver, "//*[@data-mode='checkin']");
        BaseSteps.findElementClick(driver, String.format("//*[@data-date='%s']", SpecialSteps.setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[@data-date='%s']", SpecialSteps.setDays(daysShift + daysAmount)));
        BaseSteps.findElementClick(driver, "//*[@id='xp__guests__toggle']");
        int childrenAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,'children')]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[@aria-describedby='group_children_desc'][2]", childrenAmount, childrenNeed);
        BaseSteps.findElementClick(driver, "(//*[contains(@type,'submit')])[1]");
        TimeUnit.SECONDS.sleep(3);

        BaseSteps.findElementClick(driver, "//*[@data-id='class-3']");
        BaseSteps.findElementClick(driver, "//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(3);

        SpecialSteps.findElementScrollIntoView(driver, "//*[@data-hotelid][10]");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        BaseSteps.findElementHighlight(driver, "//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        SpecialSteps.findElementSetAttribute(driver, "//*[@data-hotelid][10]", "backgroundColor", "green");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        SpecialSteps.findElementSetAttribute(driver, "//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]", "color", "red");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        BaseSteps.findElementCheckAttribute(driver, "//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]", "style", "color: red;");
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}