package tests.junit.booking.trip_moscow;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import steps.UsersApiSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingMoscowTest {
    int daysAmount = 5;
    int daysShift = 10;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    WebElement element;
    static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @BeforeClass
    public static void preCondition() {
        LOGGER.info("Start test");
        driver = Driver.getWebDriver(Config.CHROME);
    }

    @Test
    public void tripMoscowTest() throws InterruptedException {
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
        MainPage.setCityPersonRoomDates(driver, "Moscow", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(3);
        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id='group_adults']"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = driver.findElement(By.xpath("//*[@id='no_rooms']"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-sb-id='main'][contains(@type,'submit')]"))).click().perform();
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'sort_price')]/a");
        element = BaseSteps.findElementClickReturn(driver, "//*[@id='filter_price']//a[1]");
        String maxPrice = element.getText();
        maxPrice = maxPrice.substring(maxPrice.indexOf("-")).replaceAll("\\D+", "");
        TimeUnit.SECONDS.sleep(2);
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class,'bui-price-display')]/div[2]/div");
        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Finish test");
        BaseSteps.destroy(driver);
    }
}