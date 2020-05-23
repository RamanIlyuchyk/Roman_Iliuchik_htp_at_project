package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import properties.Path;
import settings.Config;
import steps.BaseSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingAddFavoritesTest {
    Properties properties;
    WebDriver driver;
    WebElement element;
    String firstHotel;
    String secondHotel;

    @Before
    public void preCondition() throws IOException {
        driver = Driver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(Path.BOOKING_PATH);
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        MainPage.bookingLogIn(driver, properties);
        MainPage.setCityPersonRoomDates(driver, "Madrid", 5, 30, 2, 0, 1);
        TimeUnit.SECONDS.sleep(5);
        setFavoritesCheckColor();
        compareHotelsIndexes(firstHotel, secondHotel);
    }

    public void setFavoritesCheckColor() throws InterruptedException {
        element = BaseSteps.findElementClickReturn(driver, "//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[1]/div/button/*[1]"));
        TimeUnit.SECONDS.sleep(3);
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        BaseSteps.findElementClick(driver, "//*[contains(@class,'bui-pagination__item')][10]");
        TimeUnit.SECONDS.sleep(6);
        List<WebElement> list = driver.findElements(By.xpath("//*[@id='hotellist_inner']/div"));
        element = BaseSteps.findElementClickReturn(driver, String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button/*[1]", (list.size() - 1))));
        TimeUnit.SECONDS.sleep(3);
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
    }

    public void compareHotelsIndexes(String firstHotel, String secondHotel) throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[@id='profile-menu-trigger--content']");
        BaseSteps.findElementClick(driver, "//*[contains(@class,'mydashboard')]");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'list_item_desc')]");
        TimeUnit.SECONDS.sleep(6);
        element = driver.findElement(By.xpath("//*[contains(@data-index,'0')]/div"));
        assertEquals(firstHotel, element.getAttribute("data-id"));
        element = driver.findElement(By.xpath("//*[contains(@data-index,'1')]/div"));
        assertEquals(secondHotel, element.getAttribute("data-id"));
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}