package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingMoscowTest {
    int daysShift = 10;
    int daysAmount = 5;
    static String BOOKING_URL = "https://www.booking.com/";
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }

    @Test
    public void bookingMoscowTest() throws InterruptedException {
        MainPage.setCityDates(driver, "Moscow", daysAmount, daysShift);

        Actions actions = new Actions(driver);
        WebElement chooseAdults = BaseSteps.findElementByCssSelector(driver, "#group_adults");
        actions.click(chooseAdults).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        WebElement chooseRooms = BaseSteps.findElementByCssSelector(driver, "#no_rooms");
        actions.click(chooseRooms).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        BaseSteps.findElementClick(driver, "//*[contains(@type,'submit')]");
        TimeUnit.SECONDS.sleep(3);

        String budget = BaseSteps.findElementClickGetText(driver, "//*[@data-id='pri-1']");
        int budgetPerNight = Integer.parseInt(budget.substring(budget.indexOf("-")).replaceAll("\\D+", ""));
        TimeUnit.SECONDS.sleep(3);

        String firstPrice = BaseSteps.findElementGetText(driver, "(//*[contains(@class,'bui-price-display')]/div[2]/div)[1]").replaceAll("\\D+", "");
        int hotelPerNight = Integer.parseInt(firstPrice) / daysAmount;
        System.out.println("Budget per night up to " + budgetPerNight);
        System.out.println("Price per night of first on the list from " + hotelPerNight);
        assertTrue("Something wrong", hotelPerNight <= budgetPerNight);
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}