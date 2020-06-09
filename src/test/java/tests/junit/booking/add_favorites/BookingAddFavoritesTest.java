package tests.junit.booking.add_favorites;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import settings.Config;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingAddFavoritesTest {
    WebElement element;
    Properties properties;
    String firstHotel;
    String secondHotel;
    int daysAmount = 5;
    int daysShift = 30;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingAddFavoritesTest.class);

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        MainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(3);
        MainPage.setCityPersonRoomDates("Madrid", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(7);
        setFavoritesCheckColor();
        compareHotelIndex(firstHotel, secondHotel);
    }

    public void setFavoritesCheckColor() throws InterruptedException {
        Driver.findElementClick("//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        element = Driver.findElementReturn("//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = Driver.findElementReturn("//*[@id='hotellist_inner']/div[1]/div[1]/div/button/*[1]");
        TimeUnit.SECONDS.sleep(3);
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        Driver.findElementClick("//*[contains(@class,'bui-pagination__item')][10]");
        TimeUnit.SECONDS.sleep(6);
        List<WebElement> list = Driver.getWebDriver().findElements(By.xpath("//*[@id='hotellist_inner']/div"));
        Driver.findElementClick(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        TimeUnit.SECONDS.sleep(5);
        element = Driver.findElementReturn(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = Driver.findElementReturn(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button/*[1]", (list.size() - 1)));
        TimeUnit.SECONDS.sleep(2);
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        System.out.println(firstHotel + " " + secondHotel);
    }

    public void compareHotelIndex(String firstHotel, String secondHotel) throws InterruptedException {
        Driver.findElementClick("//*[@id='profile-menu-trigger--content']");
        Driver.findElementClick("//*[contains(@class,'mydashboard')]");
        TimeUnit.SECONDS.sleep(3);
        Driver.findElementClick("//*[contains(@class,'list_item_desc')]");
        TimeUnit.SECONDS.sleep(5);
        element = Driver.findElementReturn("//*[contains(@data-index,'0')]/div");
        assertEquals(firstHotel, element.getAttribute("data-id"));
        element = Driver.findElementReturn("//*[contains(@data-index,'1')]/div");
        assertEquals(secondHotel, element.getAttribute("data-id"));
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}